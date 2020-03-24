DROP TABLE MEMBER CASCADE CONSTRAINTS;
DROP TABLE ADMIN CASCADE CONSTRAINTS;
DROP SEQUENCE PRODUCT_SEQ;
DROP TABLE PRODUCT CASCADE CONSTRAINTS;
DROP SEQUENCE ORDERS_SEQ;
DROP TABLE ORDERS CASCADE CONSTRAINTS;
DROP SEQUENCE REVIEW_BOARD_SEQ;
DROP TABLE REVIEW_BOARD;
DROP SEQUENCE QNA_BOARD_SEQ;
DROP TABLE QNA_BOARD;

CREATE TABLE MEMBER(
    mID VARCHAR2(50) PRIMARY KEY,
    mPW VARCHAR2(50) NOT NULL,
    mNAME VARCHAR2(50) NOT NULL,
    mEMAIL VARCHAR2(50),
    mBIRTH DATE,
    mADDRESS VARCHAR2(255),
    mPHONE VARCHAR2(50),
    mRDATE DATE
);
CREATE TABLE ADMIN(
    aID VARCHAR2(50) PRIMARY KEY,
    aPW VARCHAR2(50) NOT NULL,
    aNAME VARCHAR2(50) NOT NULL
);
CREATE SEQUENCE PRODUCT_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE TABLE PRODUCT(
    pNO NUMBER(6) PRIMARY KEY,
    pNAME VARCHAR2(50) NOT NULL,
    pFILENAME VARCHAR2(100) NOT NULL,
    pDETAIL VARCHAR2(50),
    pPRICE NUMBER(9) NOT NULL
);
CREATE SEQUENCE ORDERS_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE TABLE ORDERS(
    oNO VARCHAR2(50) PRIMARY KEY,
    mID VARCHAR2(50) REFERENCES MEMBER(MID),
    pNO NUMBER(6) REFERENCES PRODUCT(PNO),
    oADDRESS VARCHAR2(255) NOT NULL,
    oRDATE DATE DEFAULT SYSDATE,
    oQTY NUMBER(6) NOT NULL,
    oPHONE VARCHAR2(50)
);
CREATE SEQUENCE REVIEW_BOARD_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE TABLE REVIEW_BOARD(
    eNO NUMBER(6) PRIMARY KEY,
    mID VARCHAR2(50) REFERENCES MEMBER(MID),
    eHEAD VARCHAR2(100) NOT NULL,
    eCONTENT VARCHAR2(300) NOT NULL,
    eRDATE DATE DEFAULT SYSDATE,
    eIP VARCHAR2(100)
);
CREATE SEQUENCE QNA_BOARD_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
CREATE TABLE QNA_BOARD(
    bNO NUMBER(6) PRIMARY KEY,
    mID VARCHAR2(50) REFERENCES MEMBER(MID),
    aID REFERENCES ADMIN(AID),
    bHEAD VARCHAR2(100) NOT NULL,
    bCONTENT VARCHAR2(300) NOT NULL,
    bRDATE DATE DEFAULT SYSDATE,
    bHIT     NUMBER(4) DEFAULT 0,
    bGROUP   NUMBER(4) NOT NULL,
    bSTEP    NUMBER(4) NOT NULL,
    bINDENT  NUMBER(4) NOT NULL,
    bIP VARCHAR2(100)
);
----------------------------
--------MEMBER TABLE--------
----------------------------
-- ID �ߺ�üũ
SELECT * FROM MEMBER WHERE mID='aaa';
-- ȸ������
INSERT INTO MEMBER (mID, mPW, mNAME, mEMAIL, mBIRTH, mADDRESS, mPHONE)
    VALUES ('aaa','111','ȫ�浿','hong@naver.com','1990/12/12','����','010-9999-9999');
INSERT INTO MEMBER (mID, mPW, mNAME, mEMAIL, mBIRTH, mADDRESS, mPHONE)
    VALUES ('bbb','111','ȫ�浿','hong@naver.com','1990/12/12','����','010-9999-9999');
-- �α��� ID PW
SELECT * FROM MEMBER WHERE mID='aaa' and mPW='111';
-- ���ǿ� �ֱ� ���� mId�� member dto��������
SELECT * FROM MEMBER WHERE mID='aaa';
-- ��������
UPDATE MEMBER SET mPw = '111',
                  mName = 'HONG',
                  mEmail = 'yi@naver.com',
                  mBirth = '1991/12/12',
                  mAddress = '����',
                  mPHONE = '010-8888-8888'
            WHERE mId='aaa';
-- ȸ����������Ʈ
SELECT * FROM (SELECT ROWNUM RN, A.* FROM 
                        (SELECT * FROM MEMBER ORDER BY mRDATE DESC) A)
        WHERE RN BETWEEN 1 AND 10;
-- ������ ��ü ȸ�� ����
SELECT COUNT(*) cnt FROM MVC_MEMBER;
-- ȸ��Ż��
DELETE FROM MEMBER WHERE mID='aaa';
SELECT * FROM MEMBER;
COMMIT;
----------------------------
---------ADMIN TABLE--------
----------------------------
-- dummy data
INSERT INTO ADMIN VALUES ('admin','111','�����');
-- DAO�� ���� sql
-- admin loginCheck
SELECT * FROM ADMIN WHERE AID='admin' AND APW='111';
-- admin aid�� dto ��������
SELECT * FROM ADMIN WHERE AID='admin';
SELECT * FROM ADMIN;

----------------------------
-------ORDERS TABLE--------- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ �����ؾ���
----------------------------
SELECT * FROM ORDERS;
-- �ֹ� ���� LIST
SELECT * FROM (SELECT ROWNUM RN, A.* FROM(SELECT O.*, pPrice,pName FROM ORDERS O, PRODUCT P WHERE P.PNO=O.PNO ORDER BY ONO DESC) A)
    WHERE RN BETWEEN 1 AND 10;

-- �ֹ� ��ǰ ����
SELECT COUNT(*) FROM ORDERS WHERE mID='aaa';

-- �ֹ� ���� VIEW // ONO�� DTO��������
SELECT O.*, pPrice FROM ORDERS O, PRODUCT P WHERE P.PNO=O.PNO AND ONO=1;

-- �ֹ� ���� �Է�
INSERT INTO ORDERS(ONO, MID, PNO, OADDRESS, OQTY, OPHONE)
				VALUES (ORDERS_SEQ.NEXTVAL, 'aaa', 2, '���α�', 2, '010-9999-9999');
-- �ֹ� ��ǰ �󼼺���
SELECT * FROM ORDERS WHERE ONO=5;
select * from orders;
commit;
----------------------------
--------PRODUCT TABLE-------
----------------------------
-- ��ǰ ����Ʈ
SELECT * FROM PRODUCT;

SELECT * FROM (SELECT ROWNUM RN, A.* FROM(SELECT * FROM PRODUCT ORDER BY PNO DESC) A)
    WHERE RN BETWEEN 1 AND 10;
-- ��ǰ �󼼺���
SELECT * FROM PRODUCT WHERE PNO=2;
-- ��ǰ ���
INSERT INTO PRODUCT (PNO, PNAME, PFILENAME, PPRICE,PDETAIL) VALUES(PRODUCT_SEQ.NEXTVAL, '��ǰ �̸�', '�����̸�', 10000, '��ǰ ����');
INSERT INTO PRODUCT (PNO, PNAME, PFILENAME, PPRICE, PDETAIL) VALUES(PRODUCT_SEQ.NEXTVAL, '��ǰ �̸�1', '�����̸�1', 10000, '��ǰ ����1');
INSERT INTO PRODUCT (PNO, PNAME, PFILENAME, PPRICE, PDETAIL) VALUES(PRODUCT_SEQ.NEXTVAL, '��ǰ �̸�2', '�����̸�2', 10000, '��ǰ ����2');
INSERT INTO PRODUCT (PNO, PNAME, PFILENAME, PPRICE, PDETAIL) VALUES(PRODUCT_SEQ.NEXTVAL, '��ǰ �̸�3', '�����̸�3', 10000, '��ǰ ����3');
-- ��ǰ ����
UPDATE PRODUCT SET PNAME = '�׳ɴ�',
                   PFILENAME = '�̹���',
                   PPRICE = '9000',
                   PDETAIL = '�߰�����'
             WHERE PNO=4;
-- ��ǰ ����
DELETE FROM PRODUCT WHERE PNO=2;
-- ��ǰ ��ȸ
SELECT PNO, PNAME, PFILENAME, PDETAIL, PPRICE FROM PRODUCT ORDER BY PNO DESC;
--  pNo�� dto ��������
SELECT * FROM PRODUCT WHERE PNO=2;
--  ��ǰ ����
SELECT COUNT(*) FROM PRODUCT;
----------------------------
-----REVIEW_BOARD TABLE-----
----------------------------
-- �ı� �۾���
SELECT * FROM REVIEW_BOARD;
INSERT INTO REVIEW_BOARD(ENO, MID, EHEAD, ECONTENT, EIP)
				VALUES (REVIEW_BOARD_SEQ.NEXTVAL, 'bbb', '����', '����', '192.168.20.1');
-- �ı�� �󼼺���
SELECT * FROM REVIEW_BOARD WHERE ENO=1;
SELECT F.*, MNAME FROM REVIEW_BOARD F, MEMBER M WHERE M.MID=F.MID AND ENO=1;
-- �ı� �ۼ���
UPDATE REVIEW_BOARD SET EHEAD = '����',
                        ECONTENT = '����'
                  WHERE ENO=18;
-- �ı� �ۻ���
DELETE FROM REVIEW_BOARD WHERE ENO=17;
-- �ı� �۸��
SELECT * FROM (SELECT ROWNUM RN, A.* FROM(SELECT R.*, MNAME FROM REVIEW_BOARD R, MEMBER M WHERE M.MID=R.MID ORDER BY ENO DESC) A)
    WHERE RN BETWEEN 1 AND 10;
-- �۰���
SELECT COUNT(*) FROM REVIEW_BOARD;
--  eNo�� dto ��������
SELECT F.*, MNAME FROM REVIEW_BOARD F, MEMBER M WHERE M.MID=F.MID AND eNo=1;

----------------------------
-------QNA_BOARD TABLE------
----------------------------
-- QNA �۾���
INSERT INTO QNA_BOARD(BNO, MID, BHEAD, BCONTENT, BGROUP, BSTEP, BINDENT, BIP)
				VALUES (QNA_BOARD_SEQ.NEXTVAL, 'aaa', 'QNA����', 'QNA����', QNA_BOARD_SEQ.CURRVAL, 0, 0, '192.0.0.1');
-- QNA �ۼ���
UPDATE QNA_BOARD SET BHEAD = '����',
                     BCONTENT = '����',
                     BIP = '192.168.0.1'
               WHERE BNO=1;
-- QNA �ۻ���
DELETE FROM QNA_BOARD WHERE BNO=1;

-- QNA �ۻ󼼺���
SELECT Q.*, MNAME FROM QNA_BOARD Q, MEMBER M WHERE M.MID=Q.MID AND BNO=1;

-- QNA �۸��
SELECT * FROM (SELECT ROWNUM RN, A.* FROM(SELECT BNO, (SELECT MNAME FROM QNA_BOARD Q, MEMBER M WHERE Q.MID=M.MID AND QNA_BOARD.BNO=BNO) ||
          (SELECT ANAME FROM QNA_BOARD Q, ADMIN A WHERE Q.AID=A.AID AND QNA_BOARD.BNO=BNO) writer,
            BHEAD, BCONTENT, BRDATE, BHIT, BGROUP, BSTEP, BINDENT, BIP FROM QNA_BOARD ORDER BY BGROUP DESC, BSTEP) A)
    WHERE RN BETWEEN 1 AND 10;

-- QNA �۰���
SELECT COUNT(*) FROM QNA_BOARD;

-- dto���� bno��������
SELECT Q.*, MNAME FROM QNA_BOARD Q, MEMBER M WHERE M.MID=Q.MID AND BNO=3;

-- bHit �ϳ� �ø���(1���� ��ȸ�� �ϳ� �ø���)
UPDATE QNA_BOARD SET bHIT = bHIT+1 WHERE bNO=1;

-- �亯�� �߰��� STEP a ����
UPDATE QNA_BOARD SET bSTEP = bSTEP+1 WHERE bGROUP=1 AND bSTEP>0;

-- �亯�� ����
INSERT INTO QNA_BOARD (bNO, mID, aID, bHEAD, bCONTENT, bGROUP, bSTEP, bINDENT, bIP)
    VALUES (QNA_BOARD_SEQ.NEXTVAL, '�亯��', NULL, '�亯������', '��1ù��°�亯', 1, 1, '192.168.20.1');

SELECT * FROM QNA_BOARD;
COMMIT;
