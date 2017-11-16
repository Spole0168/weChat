create database yinzy character set utf8;

/***
 * 生成二维码相关的数据表
 * 微信                     系统
 * scene_id         mechineid   （临时的）
 * scene_str        
 * flag  LS  临时  YJ 永久的
 * expire_seconds 
 * ticket  返回结果   可用于查看二维码
 * url      可用于生成二维码
 */
CREATE TABLE IF NOT EXISTS wxcreatedimcode(
  id int(11) unsigned NOT NULL   auto_increment primary key,
  mechineid varchar(32) DEFAULT NULL,
  flag varchar(20) DEFAULT NULL,
  ticket varchar(400) DEFAULT NULL,
  url  varchar(400) DEFAULT NULL,
  expire_seconds datetime DEFAULT NULL,
  create_date datetime DEFAULT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/***
*处理微信和微盟的AccessToken
**/
CREATE TABLE IF NOT EXISTS commonAccessToken(
  id int(11) unsigned NOT NULL   auto_increment primary key,
  access_token varchar(200) DEFAULT NULL,
  at_type varchar(20) DEFAULT NULL,
  expires_date datetime DEFAULT NULL,
  create_date datetime DEFAULT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/***
 * 系统用户表
 */
CREATE TABLE IF NOT EXISTS yzy_admin(
  id int(11) unsigned NOT NULL   auto_increment primary key,
  userName varchar(256) DEFAULT NULL,
  displayname varchar(256) DEFAULT NULL,
  password varchar(256) DEFAULT NULL,
  email varchar(200) DEFAULT NULL,
  question varchar(500) DEFAULT NULL,
  answer varchar(500) DEFAULT NULL,
  status varchar(10) DEFAULT NULL,
  creatorusername varchar(256) DEFAULT NULL,
  lastactivitydate datetime DEFAULT NULL,
  countoflogin int(11),
  creationdate datetime DEFAULT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
/**
 * 
 * 社区服务商申请列表
 */
CREATE TABLE IF NOT EXISTS yzy_serviceapplyinfo(
  id int(11) unsigned NOT NULL   auto_increment primary key,
  communityname varchar(500)  DEFAULT NULL,
 communityaddress varchar(300) DEFAULT NULL,
 communitPersonNum int(11),
 communitrate varchar(30) DEFAULT NULL,
 watersource varchar(300) DEFAULT NULL,
 machineNum int(11),
 mechineprice	 varchar(30) DEFAULT NULL,
 waterquality varchar(300) DEFAULT NULL,
 waterTDS  varchar(30) DEFAULT NULL,
 other  varchar(300) DEFAULT NULL,
applyBusineeslic varchar(30) DEFAULT NULL,
applyorgName varchar(300) DEFAULT NULL,
applyproject varchar(500) DEFAULT NULL,
applycontactsource varchar(500) DEFAULT NULL,
applystaffnum int(11),
isIstallOK varchar(30) DEFAULT NULL,
applyorgperson varchar(100) DEFAULT NULL,
applytelphone  varchar(40) DEFAULT NULL,
applyemail varchar(300) DEFAULT NULL,
applyename varchar(100) DEFAULT NULL,
applyDate datetime DEFAULT NULL,
openid varchar(40) DEFAULT NULL,
text1 varchar(300) DEFAULT NULL,
text2 varchar(300) DEFAULT NULL,
text3 varchar(300) DEFAULT NULL,
text4 varchar(300) DEFAULT NULL,
text5 varchar(300) DEFAULT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;




---------------------------------------

/**
 * 用户申请表
 */
CREATE TABLE IF NOT EXISTS yzy_userinstallapply(
  id int(11) unsigned NOT NULL   auto_increment primary key,
  user_name varchar(300) DEFAULT NULL,
  telphone varchar(40) DEFAULT NULL,
  instak_address varchar(500) DEFAULT NULL,
   openid varchar(100) DEFAULT NULL,
   apply_Date datetime DEFAULT NULL,
 text1 varchar(300) DEFAULT NULL,
 text2 varchar(300) DEFAULT NULL,
text3 varchar(300) DEFAULT NULL,
text4 varchar(300) DEFAULT NULL,
text5 varchar(300) DEFAULT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
	

/**
 *yzy_repairlog  维修记录
 */
CREATE TABLE
    yzy_repairlog
    (
        id INT unsigned NOT NULL AUTO_INCREMENT,
        machineid INT unsigned,
        serviceUserid VARCHAR(100),
        servicepoint VARCHAR(500),
        servicejudge VARCHAR(500),
        waterTDS VARCHAR(100),
        outputTDS VARCHAR(100),
         repairtime datetime,
        scaleflag VARCHAR(10),
        text1 VARCHAR(300),
        text2 VARCHAR(300),
        text3 VARCHAR(300),
        text4 VARCHAR(300),
        text5 VARCHAR(300),
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 *yzy_userInfo 用户表
 */
CREATE TABLE
    yzy_userinfo
    (
        id INT unsigned NOT NULL AUTO_INCREMENT,
        contact_name VARCHAR(300),
        telphone VARCHAR(40),
        cardNo VARCHAR(40),
        instak_address VARCHAR(500),
        active_Date DATETIME,
        mechinename VARCHAR(300),
        machineid VARCHAR(300),
        userinfoid INT unsigned,
        machetype VARCHAR(500),
        openid VARCHAR(100),
        inputTDS VARCHAR(100),
        outputTDS VARCHAR(100),
        scaleflag VARCHAR(10),
        judge VARCHAR(10),
        taste VARCHAR(10),
        create_Date DATETIME,
        serviceTimes INT unsigned,
        text1 VARCHAR(300),
        text2 VARCHAR(300),
        text3 VARCHAR(300),
        text4 VARCHAR(300),
        text5 VARCHAR(300),
         text6 VARCHAR(300),
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
    

/**
 *yzy_watermachine 用户净水机信息
 */
CREATE TABLE IF NOT EXISTS yzy_watermachine(
  id int(11) unsigned NOT NULL   auto_increment primary key,
 text1 varchar(300) DEFAULT NULL,
 text2 varchar(300) DEFAULT NULL,
text3 varchar(300) DEFAULT NULL,
text4 varchar(300) DEFAULT NULL,
text5 varchar(300) DEFAULT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


/**
 *yzy_appoitmentcheck 用户预约检测
 */
CREATE TABLE IF NOT EXISTS yzy_appoitmentcheck(
  id int(11) unsigned NOT NULL   auto_increment primary key,
create_Date	 datetime,
appoit_date datetime,
openid varchar(100),
userid int(11)unsigned,
machineid int(11)unsigned,
status varchar(40),
replymsg varchar(600),
 text1 varchar(300) DEFAULT NULL,
 text2 varchar(300) DEFAULT NULL,
text3 varchar(300) DEFAULT NULL,
text4 varchar(300) DEFAULT NULL,
text5 varchar(300) DEFAULT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table yzy_openidcode(
  id int(11) unsigned NOT NULL   auto_increment primary key,
 opencode varchar(500) DEFAULT NULL,
 openid varchar(500) DEFAULT NULL,
 create_date datetime DEFAULT null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 做地址跳转的作用
 */
CREATE TABLE IF NOT EXISTS yzy_redict(
  id int(11) unsigned NOT NULL   auto_increment primary key,
  flag varchar(20) DEFAULT NULL,
  urlpath varchar(500) DEFAULT NULL,
  create_Date datetime DEFAULT NULL,
 text1 varchar(300) DEFAULT NULL,
 text2 varchar(300) DEFAULT NULL,
text3 varchar(300) DEFAULT NULL,
text4 varchar(300) DEFAULT NULL,
text5 varchar(300) DEFAULT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
