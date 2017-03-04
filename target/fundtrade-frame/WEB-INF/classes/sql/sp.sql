-- ----------------------------
-- 基金账号插入
-- ----------------------------
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_addTaAcco$$
CREATE PROCEDURE sp_addTaAcco(in vc_custno varchar(18), vc_tacode varchar(8))
begin
  declare vc_taacco_previous varchar(12) default null; -- 上一条记录
  declare vc_taacco_current varchar(12) default null; -- 当前记录
  declare vc_opendate varchar(10) default null;
  select vc_taacco into vc_taacco_previous from taacco order by vc_taacco desc limit 1;
  set vc_opendate = CURDATE();
  if vc_taacco_previous is null
    then
      INSERT INTO taacco(vc_custno, vc_taacco, vc_opendate, vc_tacode)
      VALUES (vc_custno,'000000000000',vc_opendate,vc_tacode);
    ELSE
      set vc_taacco_current = vc_taacco_previous + 1;
      set vc_taacco_current = LPAD(vc_taacco_current,12,'0');
      INSERT INTO taacco(vc_custno, vc_taacco, vc_opendate, vc_tacode)
      VALUES (vc_custno,vc_taacco_current,vc_opendate,vc_tacode);
  END IF;
end$$
DELIMITER ;


-- ----------------------------
-- 交易账号插入
-- ----------------------------
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_addTradeAcco$$
CREATE PROCEDURE sp_addTradeAcco(in vc_custno varchar(18), vc_bankname varchar(60), `vc_bankacco` varchar(28))
begin
  declare vc_tradeacco_previous varchar(17) default null; -- 上一条记录
  declare vc_tradeacco_current varchar(17) default null; -- 当前记录
  declare vc_opendate varchar(10) default null;
  select vc_tradeacco into vc_tradeacco_previous from tradeacco order by vc_tradeacco desc limit 1;
  set vc_opendate = CURDATE();
  if vc_tradeacco_previous is null
    then
      INSERT INTO tradeacco(vc_custno, vc_tradeacco, vc_opendate, vc_bankname, vc_bankacco)
      VALUES (vc_custno,'00000000000000000',vc_opendate,vc_bankname,vc_bankacco);
    ELSE
      set vc_tradeacco_current = vc_tradeacco_previous + 1;
      set vc_tradeacco_current = LPAD(vc_tradeacco_current,17,'0');
      INSERT INTO tradeacco(vc_custno, vc_tradeacco, vc_opendate, vc_bankname, vc_bankacco)
      VALUES (vc_custno,vc_tradeacco_current,vc_opendate,vc_bankname,vc_bankacco);
  END IF;
end$$
DELIMITER ;

-- ----------------------------
-- 客户信息插入
-- ----------------------------
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_addCustInfo$$
CREATE PROCEDURE sp_addCustInfo(in c_custtype char(1), vc_custname varchar(64), vc_identityno varchar(32), vc_tacode varchar(8), mobile varchar(15), email varchar(50), address varchar(200))
  begin
    declare vc_custno_previous varchar(18) default null; -- 上一条记录
    declare vc_custno_current varchar(18) default null; -- 当前记录
    declare vc_opendate varchar(10) default null;
    select vc_custno into vc_custno_previous from custinfo order by vc_custno desc limit 1;
    set vc_opendate = CURDATE();
    if vc_custno_previous is null
    then
      INSERT INTO custinfo(vc_custno, c_custtype, vc_custname, vc_identityno, vc_tacode, mobile, email, address, vc_opendate)
      VALUES ('000000000000000000',c_custtype,vc_custname,vc_identityno,vc_tacode,mobile,email,address,vc_opendate);
    ELSE
      set vc_custno_current = vc_custno_previous + 1;
      set vc_custno_current = LPAD(vc_custno_current,18,'0');
      INSERT INTO custinfo(vc_custno, c_custtype, vc_custname, vc_identityno, vc_tacode, mobile, email, address, vc_opendate)
      VALUES (vc_custno_current,c_custtype,vc_custname,vc_identityno,vc_tacode,mobile,email,address,vc_opendate);
    END IF;
  end$$
DELIMITER ;