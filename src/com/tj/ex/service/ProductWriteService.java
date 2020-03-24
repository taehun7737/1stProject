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
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.tj.ex.dao.ProductDao;
public class ProductWriteService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 파일첨부 로직 + 파라미터들 받아 DB에 join
		String path = request.getRealPath("productFileBoardUp");
		int maxSize = 1024*1024*10; // 최대업로드 사이즈는 10M
		MultipartRequest mRequest = null;
		String pFileName = "";
		try {
			mRequest = new MultipartRequest(request, path, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<String> params = mRequest.getFileNames();
			String param = params.nextElement();
			pFileName = mRequest.getFilesystemName(param);
			HttpSession httpSession = request.getSession();
			String pName = mRequest.getParameter("pName");
			int pPrice = Integer.parseInt(mRequest.getParameter("pPrice"));
			String pDetail = mRequest.getParameter("pDetail");
			ProductDao boardDao = ProductDao.getInstance();
			int result = boardDao.productRegister(pName, pFileName, pPrice, pDetail);
			if(result == ProductDao.SUCCESS) {
				request.setAttribute("resultMsg", "상품 등록 성공");
			}else {
				request.setAttribute("resultMsg", "상품 등록 실패");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			request.setAttribute("resultMsg", "상품 등록 실패");
		}
		// 서버에 올라간 fileboardUp 파일을 소스폴더에 filecopy
		File serverFile = new File(path+"/"+pFileName);
		if(serverFile.exists()) {
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
				System.out.println(e.getMessage());
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