package com.tj.ex.service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tj.ex.dao.ProductDao;
public class ProductModifyService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("productFileBoardUp");
		int maxSize = 1024*1024*10; // 최대업로드 사이즈는 10M
		MultipartRequest mRequest = null;
		String pFileName = "";
		String dbFileName = "";
		try {
			mRequest = new MultipartRequest(request, path, maxSize, 
									"utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			pFileName = mRequest.getFilesystemName(param);
			dbFileName = mRequest.getParameter("dbFileName");
			if(pFileName==null) {
				pFileName = dbFileName;
			}
			String pName = mRequest.getParameter("pName");
			int pPrice = Integer.parseInt(mRequest.getParameter("pPrice"));
			String pDetail = mRequest.getParameter("pDetail");
			int pNo = Integer.parseInt(mRequest.getParameter("pNo"));
			ProductDao pDao = ProductDao.getInstance();
			int result = pDao.productModify(pName, pFileName, pPrice, pDetail, pNo);
			// joinMember결과에 따라 적절히 request.setAttribute
			if(result == ProductDao.SUCCESS) { // 회원가입 진행
				request.setAttribute("resultMsg", "상품 수정 성공");
			}else {
				request.setAttribute("resultMsg", "상품 수정 실패");
			}
			// pageNum이 request객체에서 온 것은 null값이라 request에 담아놓음
			String pageNum = mRequest.getParameter("pageNum");
			request.setAttribute("pageNum", pageNum);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("resultMsg", "상품 수정 실패");
		}
		// 서버에 올라간 fileboardUp 파일을 소스폴더에 filecopy
		File serverFile = new File(path+"/"+pFileName);
		if(!pFileName.equals(dbFileName) && serverFile.exists()) {
			InputStream  is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(serverFile);
				os = new FileOutputStream("D:/오전수업/mege_IT/SOURCE/7_jQuery/chiken/WebContent/productFileBoardUp/"+pFileName);
				byte[] bs = new byte[(int)serverFile.length()];
				while(true) {
					int nByteCnt = is.read(bs);
					if(nByteCnt==-1) break;
					os.write(bs, 0, nByteCnt);
				}
			} catch (Exception e) {
				System.out.println("파일복사 예외 : "+e.getMessage());
			} finally {
				try {
					if(os!=null) os.close();
					if(is!=null) is.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
}