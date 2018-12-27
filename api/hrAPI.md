# backend\src\main\java\com\elex\oa\controller\hr\ChangeInformationController.java:
ChangeInformation{
    "changeinformation":""   变更项目
    "changedtruename":""   员工姓名
    "beforeinformation":""   变更前信息
    "afterinformation":""   变更后信息
    "changereason":""   变更原因
    "changedate":""   开始查询日期
    "transactortruename":""   变更人姓名
}
## @RequestMapping("/queryChangeInformations")
获得变更信息的列表信息（分页）
### Request.json
{
    "page":""  Integer  require 页码
    "rows":""  Integer  option 每页数量
    "changeinformation":"" String option 变更项目
    "changeinformations":"" String option 变更项目复数
    "changeinformationvalue":"" String option 变更项目查询条件
    "changedtruename":"" String option 员工姓名
    "changedtruenames":"" String option 员工姓名复数
    "changedtruenamevalue":"" String option 员工姓名查询条件
    "beforeinformation":"" String option 变更前信息
    "beforeinformations":"" String option 变更前信息复数
    "beforeinformationvalue":"" String option 变更前信息查询条件
    "afterinformation":"" String option 变更后信息
    "afterinformations":"" String option 变更后信息复数
    "afterinformationvalue":"" String option 变更后信息查询条件
    "changereason":"" String option 变更原因
    "changereasons":"" String option 变更原因复数
    "changereasonvalue":"" String option 变更原因查询条件
    "changedatevlaue1":"" String option 开始查询日期
    "changedatevlaue2":"" String option 结束查询日期
    "transactortruename":"" String option 变更人姓名
    "transactortruenames":"" String option 变更人姓名复数
    "transactortruenamevalue":"" String option 变更人查询条件
}

### ResponseBody
{
    "list":[ChangeInformation]
    "total": "" Integer 查询数量
}

## @RequestMapping("/queryAllChangeInformations")
获得变更信息的列表信息（不分页）
### Request.json
{
}

### ResponseBody
{
    [ChangeInformation]
}

## @RequestMapping("/deleteChangeinformationByIds")
根据IDs删除变更信息
### Request.json
{
    "ids":[] Integer[] require 变更信息ID数组
}

### ResponseBody
{
""
}

## @RequestMapping("/importChangeinformations")
变更信息的导入
### Request.json
{
"file":binary 文件
}

### ResponseBody
{
    ""
}

#backend\src\main\java\com\elex\oa\controller\hr\ContractInformationController.java:
ContractInformation{
     "truename": ""String  员工姓名
     "employeenumber":""String  员工号
     "contractcode":"" String  合同编号
     "contracttype":"" String  合同类型
     "contractage":""" String  合同期限
     "remark":""" String 备注
}


## @RequestMapping("/queryContractInformations")
   获得合同列表信息（分页）
### Request.json
{
   "page":""  Integer  require 页码
   "rows":""  Integer  require 每页数量
   "truename":"" String option 员工姓名
   "truenames":"" String option 员工姓名复数
   "truenamevalue":"" String option 员工姓名查询条件
   "employeenumber":"" String option 员工号
   "employeenumbers":"" String option 员工号复数
   "employeenumbervalue":"" String option 员工号查询条件
   "contractcode":"" String option 合同编号
   "contractcodes":"" String option 合同编号复数
   "contractcodevalue":"" String option 合同编号查询条件
   "contracttype":"" String option 合同类型
   "contracttypes":"" String option 合同类型复数
   "contracttypevalue":"" String option 合同类型查询条件
   "contractage":"" String option 合同期限
   "contractages":"" String option 合同期限复数
   "contractagevalue":"" String option 合同期限查询条件
   "startdatevalue1":"" String option 开始查询时间
   "startdatevalue2":"" String option 结束查询时间
}

### ResponseBody
{
"list":  [ContractInformation],
"total":""  查询数量
}

## @RequestMapping("/queryContractsByUserid")
   根据userid获得合同信息
### Request.json
{
    "userid":"" Integer require 账号ID
}

### ResponseBody
{
    [ContractInformation]
}

## @RequestMapping("/queryOneContractInformation")
  根据合同ID获得合同信息（已过时）
### Request.json
{
    "contractId":"" Integer require 合同ID
}

### ResponseBody
{
   "truename":""   员工姓名
   "employeenumber":""   员工号
   "contractcode":""   合同编号
   "contracttype":""   合同类型
   "contractage":""   合同期限
   "remark":""  备注
   "transactortruename":""  办理人姓名
   "historyContract:[ContractInformation]
}

## @RequestMapping("/queryContractsById")
  根据合同ID获得合同信息
### Request.json
{
    "contractid":"" Integer require 合同ID
}

### ResponseBody
{
   "truename":""   员工姓名
   "employeenumber":""   员工号
   "contractcode":""   合同编号
   "contracttype":""   合同类型
   "contractage":""   合同期限
   "remark":""  备注
   "transactortruename":""  办理人姓名
   "historyContract:[ContractInformation]
}

## @RequestMapping("/addContractInformation")
  添加一条合同信息
### Request.json
{
  "contractcode":""  String  require 合同编号 
  "userid":"" Integer require 账号ID
  "startdate":"" String require 合同生效期
  "enddate":"" String require 合同生效期
  "contracttypeid":"" Integer option 合同类型ID
  "attfile":"" binary option 合同附件
  "transactorusername":"" String require 办理人
}

### ResponseBody
{
""
}

## @RequestMapping("/updateContractInformation")
  更新一条合同信息
### Request.json
{
  "id":"" Integer require 合同ID
  "contractcode":"" String 合同编号
  "userid":"" Integer require 账号ID
  "startdate":"" String require 合同生效期
  "enddate":"" String require 合同生效期
  "contracttypeid":"" Integer option 合同类型ID
  "attfile": binary option 合同附件
  "remark":"" String 合同备注
  "transactorusername":"" String require 办理人
}

### ResponseBody
{
""
}

## @RequestMapping("/queryAllContractInformations")
  查询所有的合同信息
### Request.json
{
}

### ResponseBody
{
[ContractInformation]
}

## @RequestMapping("/deleteContractsByIds")
  根据IDs删除合同信息
### Request.json
{
"contractids":[] Integer[] require 合同ID复数
}

### ResponseBody
{
""
}

## @RequestMapping("/importContractinformations")
  合同信息的导入
### Request.json
{
"file": binary require 导入文件
}

### ResponseBody
{
""
}

#backend\src\main\java\com\elex\oa\controller\hr\DepartmentInformationController.java:
user{
    "id": ,账号ID
    "username": "",登录ID
    "password": "",初始密码
    "truename": "",姓名
    "isactive": ,是否激活
    "state": ,在职状态
    "employeenumber": ""员工号
}
department{
    "id":"" Integer 部门ID
    "companyname":"" String 公司名称
    "depname":"" String 部门名称
    "depcode":"" String 部门编号
    "functionaltypeid":"" Integer 职能类型ID
    "functionaltype":"" String 职能类型
    "parentdepid":"" Integer 上级部门ID
    "principaluserid":"" Integer 部门正职账号ID
    "principaluser": user
    "deputyuserid":"" Integer 部门副职账号ID
    "deputyuser":user
}
## @RequestMapping("/queryOneDepByDepname")
   根据部门名称查询部门信息
### Request.json
{
"title":"" String require 部门名称
}

### ResponseBody
{
department
}

## @RequestMapping("/queryOneDepByDepcode")
  根据部门编号查询部门信息
### Request.json
{
"code":"" String require 部门编号
}

### ResponseBody
{
department
}

## @RequestMapping("/queryDepartments")
  查询所有的部门信息
### Request.json
{
}

### ResponseBody
{
[department]
}

## @RequestMapping("/queryDepartmentsRemoveChilren")
  根据depid查询可选的上级部门
### Request.json
{
"depid":"" Integer require 上级部门ID
}

### ResponseBody
{
[department]
}

depTree{
"title":"" 部门名称
"code":"" 部门编号
"id":"" 部门排序号
"expand":"" 节点是否展开
"children":[depTree] 子节点
}
## @RequestMapping("/listDepts")
  查询部门树信息
### Request.json
{
}

### ResponseBody
{
depTree
}

## @RequestMapping("/addOneDepartment")
  添加一条部门信息
### Request.json
{
depcode: String 部门编号
depname: String 部门名称
}

### ResponseBody
{
""
}

## @RequestMapping("/queryOneByDepid")
  根据depid查询一条部门信息
### Request.json
{
id:  string,  require/ 部门ID
}

### ResponseBody
{
}

## @RequestMapping("/updateOneDepartment")
  更新部门信息
### Request.json
{
id:  string,  require/ 部门ID
}

### ResponseBody
{
""
}

## @RequestMapping("/deleteDeptsById")
  根据depid级联删除部门信息
### Request.json
{
id:  string,  require/ 部门ID
}

### ResponseBody
{
}

## @RequestMapping("/queryDeptLogInformations")
  查询部门日志列表信息（分页）
### Request.json
{
page: Integer 页码
rows: Integer 条数/每页
}

### ResponseBody
{
list:[部门日志对象]
total: 总条数
}

 ## @RequestMapping("/queryAllDeptLogInformations")
 查询所有的部门日志信息（不分页）
### Request.json
{
}

### ResponseBody
{
[部门日志对象]
}

 ## @RequestMapping("/deleteDeplogByIds")
 根据IDs删除部门日志信息
### Request.json
{
ids:[] 部门ID数组
}

### ResponseBody
{
""
}

 ## @RequestMapping("/importDeploginformations")
 部门日志信息导入
### Request.json
{
file:binary 文件
}

### ResponseBody
{
""
}

 ## @RequestMapping("/sortDepinformation")
 部门排序--》根据父节点查询所有的同级数据
### Request.json
{
title: String  部门名称
}

### ResponseBody
{
[{title:部门名称,code:排序码}]
}

 ## @RequestMapping("/submitSortdata")
 对数据进行排序
### Request.json
{
[{title:部门名称,code:排序码}]
}

### ResponseBody
{
""
}

 ## @RequestMapping("/queryHRManageCard")
 根据初始时间、公司名称查询管理看板信息
### Request.json
{
companyname:公司名称
}

### ResponseBody
{
    "head": {
        "rspCode": "205", 编码
        "rspMsg": "返回成功！" 弹出消息
    },
    "body": {
        "HRManageCards": [
            {
                "depname": "江苏博智软件科技南京总部", 部门名称
                "deptid": 111, 部门ID
                "parentid": null, 上级部门ID
                "num": 0, 部门总人数
                "ratio": "0.0%", 占比
                "intoNum": 0, 入职人数
                "outNum": 0, 离职人数
                "users": null, 总人数消息
                "childDepts": null 子部门
            }
        ],
        "outNum": 0, 离职总人数
        "allNum": 151, 总人数
        "intoNum": 0 入职总人数
    }
}

 ## @RequestMapping("/queryHRManageCard2")
 获得总人数(edate时间点的在职总人数)
 
 personalinformation
 {
     "id": 2197, 
     "userid": 2368,
     "employeenumber": "000239",
     "employeenumbers": null,
     "employeenumbervalue": "",
     "sex": "男",
     "depid": 9,
     "telphone": "",
     "telphones": null,
     "telphoneid": null,
     "telphonevalue": "",
     "mobilephone": "13776407753",
     "mobilephones": null,
     "mobilephonevalue": "",
     "baseinformationid": 2207,
     "manageinformationid": 2186,
     "costinformationid": 2152,
     "otherinformationid": 2161,
     "company": "江苏博智软件科技南京总部",
     "depname": "技术研发部",
     "depcode": "",
     "depnames": null,
     "depnamevalue": "",
     "principaltruename": "管理员",
     "principalemployeenumber": "admin",
     "postids": [
         175
     ],
     "postList": null,
     "postnames": "大数据",
     "postname": "",
     "postnameList": null,
     "postnamevalue": "",
     "perids": null,
     "ppids": null,
     "isactive": 1,
     "username": "郑逢强",
     "usernames": null,
     "usernamevalue": "",
     "truename": "郑逢强",
     "truenames": null,
     "truenamevalue": "",
     "state": "",
     "userphoto": "",
     "idphoto1": "",
     "idphoto2": "",
     "englishname": "",
     "englishnames": null,
     "englishnamevalue": "",
     "idcode": "410726198405101219",
     "idcodes": null,
     "idcodevalue": "",
     "birthday": "1984/05/10",
     "birthdayvalue1": null,
     "birthdayvalue2": null,
     "age": "34",
     "ages": null,
     "agevalue": "",
     "ageMap": null,
     "sbir": "",
     "ebir": "",
     "constellation": "金牛座",
     "constellations": null,
     "constellationvalue": "",
     "chinesecs": "鼠",
     "chinesecses": null,
     "chinesecsvalue": "",
     "race": "汉族",
     "races": null,
     "racevalue": "",
     "marriage": "",
     "children": "",
     "childrens": null,
     "zzmm": "群众",
     "zzmms": null,
     "zgxl": "硕士",
     "zgxls": null,
     "byyx": "哈尔滨工业大学",
     "byyxs": null,
     "byyxvalue": "",
     "sxzy": "计算机",
     "sxzys": null,
     "sxzyvalue": "",
     "pyfs": "统招",
     "pyfses": null,
     "firstla": "",
     "firstlas": null,
     "elsela": "",
     "elselas": null,
     "posttitle": "",
     "posttitles": null,
     "zyzstype": "",
     "zyzstypes": null,
     "zyzstypevalue": "",
     "zyzsname": "",
     "zyzsnames": null,
     "zyzsnamevalue": "",
     "firstworkingtime": "2009/07/01",
     "firstworkingtimevalue1": "",
     "firstworkingtimevalue2": "",
     "workingage": "9",
     "workingages": null,
     "workingagevalue": "",
     "workingageMap": null,
     "sfwt": "",
     "efwt": "",
     "parentcompany": "",
     "parentcompanys": null,
     "parentcompanyvalue": "",
     "baseinformationids": null,
     "hj": "",
     "zj": "M0职员级",
     "entrydate": "",
     "sn": "",
     "zhuanzhengdate": "",
     "employeetype": "正式员工",
     "salary": "",
     "ssb": "",
     "ssbgscd": "",
     "ssbgrcd": "",
     "gjj": "",
     "gjjgscd": "",
     "gjjgrcd": "",
     "khh": "",
     "salaryaccount": "",
     "sbjnd": "",
     "sbcode": "",
     "gjjcode": "",
     "privateemail": "254680501@qq.com",
     "companyemail": "zhengfengqiang@elextec.com",
     "emergencycontract": "张利娜",
     "emergencyrp": "",
     "emergencyphone": "13701401841",
     "address": "江苏省南京市江宁区",
     "remark": null
 }
### Request.json
{
"sdate"：String sdate, 开始查询时间
"edate"：String edate, 结束查询时间
"companyname"：String companyname, 公司名称
"rows"：Integer rows, 条数/每页
"page"：Integer page 页码
}

### ResponseBody
{
    "head": {
        "rspCode": "205",
        "rspMsg": "提交成功！"
    },
    "body": {
        "total": 151,
        "list": [
            personalinformation
        ],在职人事信息
        "allList": [
            personalinformation
        ]所有人事信息
    }
}

 ## @RequestMapping("/queryHRManageCard3")
 获得入职总人数(edate时间点的入职总人数)
### Request.json
{
"sdate"：String sdate, 开始查询时间
"edate"：String edate, 结束查询时间
"companyname"：String companyname, 公司名称
"rows"：Integer rows, 条数/每页
"page"：Integer page 页码
}

### ResponseBody
{
    "head": {
        "rspCode": "205",
        "rspMsg": "提交成功！"
    },
    "body": {
        "total": 151,
        "list": [
            personalinformation
        ],入职人事信息
        "allList": [
            personalinformation
        ]所有人事信息
    }
}

 ## @RequestMapping("/queryHRManageCard4")
 获得离职总人数(edate时间点的离职总人数)
### Request.json
{
"sdate"：String sdate, 开始查询时间
"edate"：String edate, 结束查询时间
"companyname"：String companyname, 公司名称
"rows"：Integer rows, 条数/每页
"page"：Integer page 页码
}

### ResponseBody
{
    "head": {
        "rspCode": "205",
        "rspMsg": "提交成功！"
    },
    "body": {
        "total": 151,
        "list": [
            personalinformation
        ],离职人事信息
        "allList": [
            personalinformation
        ]所有人事信息
    }
}

 ## @RequestMapping("/queryHRManageCard5")
 获得所在部门在职人数(edate时间点的在职总人数)(注意转部门员工的影响，注：此种情况前期版本暂不考虑)
### Request.json
{
"sdate"：String sdate, 开始查询时间
"edate"：String edate, 结束查询时间
"depid"：Integer depid, 部门ID
"rows"：Integer rows, 条数/每页
"page"：Integer page 页码
}

### ResponseBody
{
    "head": {
        "rspCode": "205",
        "rspMsg": "提交成功！"
    },
    "body": {
        "total": 151,
        "list": [
            personalinformation
        ],所在部门在职人事信息
        "allList": [
            personalinformation
        ]所在部门所有人事信息
    }
}

 ## @RequestMapping("/queryHRManageCard6")
 获得所在部门入职人数(edate时间点的入职总人数)(注意转部门员工的影响，注：此种情况前期版本暂不考虑)
### Request.json
{
"sdate"：String sdate, 开始查询时间
"edate"：String edate, 结束查询时间
"depid"：Integer depid, 部门ID
"rows"：Integer rows, 条数/每页
"page"：Integer page 页码
}

### ResponseBody
{
    "head": {
        "rspCode": "205",
        "rspMsg": "提交成功！"
    },
    "body": {
        "total": 151,
        "list": [
            personalinformation
        ],所在部门入职人事信息
        "allList": [
            personalinformation
        ]所在部门所有人事信息
    }
}

 ## @RequestMapping("/queryHRManageCard7")
 获得所在部门离职人数(edate时间点的入职总人数)(注意转部门员工的影响，注：此种情况前期版本暂不考虑)
### Request.json
{
"sdate"：String sdate, 开始查询时间
"edate"：String edate, 结束查询时间
"depid"：Integer depid, 部门ID
"rows"：Integer rows, 条数/每页
"page"：Integer page 页码
}

### ResponseBody
{
    "head": {
        "rspCode": "205",
        "rspMsg": "提交成功！"
    },
    "body": {
        "total": 151,
        "list": [
            personalinformation
        ],所在部门离职人事信息
        "allList": [
            personalinformation
        ]所在部门所有人事信息
    }
}

 ## @RequestMapping("/queryIOSData")
 IOS的通讯录数据（根据depid查询人事概述信息：姓名）
### Request.json
{
"depid": Integer 部门ID
}

### ResponseBody
{
"depname": "江苏博智软件科技股份有限公司",
"deptid": 13,
"parentid": null,
"num": null,
"ratio": null,
"intoNum": null,
"outNum": null,
"users":[
       {
            "truename": "超级用户",
            "mobilephone": "",
            "id": 2315,
            "deptname": "江苏博智软件科技股份有限公司"
        }
],
"childDepts":[
        {
            "id": 111,
            "parentdep": null,
            "parentdepid": 13,
            "companyname": "江苏博智软件科技南京总部",
            "depname": "江苏博智软件科技南京总部",
            "depcode": "ELEX99",
            "functionaltype": null,
            "functionaltypeid": 7,
            "principaluser": null,
            "principaluserid": 1,
            "deputyuser": null,
            "deputyuserid": null,
            "secretaryuser": null,
            "secretaryuserid": null,
            "numbers": null,
            "dutydescription": "1",
            "depdescription": "",
            "state": 1,
            "ordercode": 0,
            "deptypeid": 134,
            "deptype": null
        }
]
}

 ## @RequestMapping("/validateDeptnameForAddDept")
 添加部门的时候校验部门名称是否存在(已过时)
### Request.json
{

}

### ResponseBody
{
}

 ## @RequestMapping("/validateDeptcodeForAddDept")
 添加部门的时候校验部门编号
### Request.json
{
"deptcode": String 部门编号
}

### ResponseBody
{
Boolean
}

#backend\src\main\java\com\elex\oa\controller\hr\DimissionController.java:

## @RequestMapping("/addDimission")
   添加离职信息
### Request.json
{
    "transactorusername":"" 登录人账号
    "dimissionuserid":""  离职人员账号ID
    "lastworkingdate":""  最后工作时间
    "dimissiontypeid":""  离职类型ID
    "dimissionreasonid":""  离职原因ID
    "dimissiondirectionid":""  离职方向ID
}

### ResponseBody
{
""
}

## @RequestMapping("/queryDimissionInformations")
   查询离职列表信息（分页）
### Request.json
{
"page":"""页码
"rows":""条数/每页
}

### ResponseBody
{
"list":[离职对象]
"total":总条数
}

## @RequestMapping("/queryAllDimissionInformations")
  查询所有的离职信息（不分页）
### Request.json
{
}

### ResponseBody
{
[离职对象]
}

## @RequestMapping("/modifyDimissionInformationById")
  根据ID修改离职信息
### Request.json
{
"dimissionid":"" Integer 离职ID
}

### ResponseBody
{
""
}

## @RequestMapping("/queryDimissionInformationById")
  根据ID查询离职信息
### Request.json
{
"dimissionid":"" Integer 离职ID
}

### ResponseBody
{
离职对象
}

## @RequestMapping("/removeDimissionInformations")
  根据ID删除离职信息
### Request.json
{
"dimissionids":[] Integer[] 离职ID数组
}

### ResponseBody
{
""
}

## @RequestMapping("/importDimissionInformations")
  离职信息导入
### Request.json
{
"file": binary 文件
}

### ResponseBody
{
""
}

#backend\src\main\java\com\elex\oa\controller\hr\PersonalInformationController.java:

## @RequestMapping("/PersonalInformationController/queryPersonalInformations")
   人事列表信息展示（数据初始化）
### Request.json
{
"page": Integer 页码
"rows": Inteber 条数/每页
}

### ResponseBody
{
"pagedata":{
            "list":[personalinformation]
            "total": 条数
 }
 "selections":{
        "byyxs":[String], 毕业院校
        "employeenumbers":[String],员工号
        "zzmms":[String],政治面貌
        "sxzys":[String],所学专业
        "postnames":[String],岗位名称
        "telphones":[String],办公电话
        "truenames":[String],姓名
        "posttitles":[String],职称
        "zyzsnames":[String],职业证书名称
        "childs":[String],生育
        "idcodes":[String],身份证号
        "zgxls":[String],最高学历
        "parentcompanys":[String],上家雇主
        "races":[String],民族
        "flas":[String],外语
        "ages":[String],年龄
        "usernames":[String],账号
        "englishnames":[String],英文名
        "mobilephones":[String],移动电话
        "depnames":[String],部门名称
        "zyzstypes":[String],职业证书类型
        "pyfss":[String],培养方式
        "workingages":[String],工作年限
 }
}

## @RequestMapping("/queryPersonalInformations")
   人事信息查询（分页）
### Request.json
{
"page": Integer 页码
"rows": Inteber 条数/每页
}

### ResponseBody
{
"list":[personalinformation]
"total": 条数
}

## @RequestMapping("/queryPersonalInformationById")
  根据ID查询人事信息
### Request.json
{
"personalInformationId":人事信息ID
}

### ResponseBody
{
personalinformation
}

## @RequestMapping("/queryPersonalInformationByUserid")
  根据userid查询人事信息
### Request.json
{
"userid":账号ID
}

### ResponseBody
{
personalinformation
}

## @RequestMapping("/queryPersonalInformationByTruename")
  根据姓名查询人事信息
### Request.json
{
"truename":姓名
}

### ResponseBody
{
personalinformation
}

## @RequestMapping(value = "/addBaseInformation", consumes = "multipart/form-data")
  添加人事基础信息
### Request.json
{
personalinformation:人事信息,
"byyxvalue":毕业院校是否录入
"sxzyvalue":所学专业是否录入
"zyzsnamevalue":职业证书名称是否录入
"parentcompanyvallue":上家雇主是否录入
"lysqdid":录用申请单ID
"idphoto22": binary 身份证照片（正面）
"idphoto11": binary 身份证照片（背面）
"userphoto2": binary 免冠照片
}

### ResponseBody
{
""
}

## @RequestMapping("/addManageInformation")
  添加人事管理信息
### Request.json
{
personalinformation
}

### ResponseBody
{
""
}

## @RequestMapping("/addCostInformation")
  添加人事成本信息
### Request.json
{
personalinformation:人事信息,
"khhvalue":开户行是否录入
"sbjndvalue":社保缴纳地是否录入
}

### ResponseBody
{
""
}

## @RequestMapping("/addOtherInformation")
  添加人事其它信息
### Request.json
{
personalinformation
}

### ResponseBody
{
""
}

## @RequestMapping("/updateBaseInformation")
  修改人事基础信息
### Request.json
{
personalinformation:人事信息
"byyxvalue":毕业院校是否录入
"sxzyvalue":所学专业是否录入
"zyzsnamevalue":职业证书名称是否录入
"parentcompanyvallue":上家雇主是否录入
"transactorusername":办理人账号
"idphoto22": binary 身份证照片（正面）
"idphoto11": binary 身份证照片（背面）
"userphoto2": binary 免冠照片
}

### ResponseBody
{
""
}

 ## @RequestMapping("/updateManageInformation")
 修改人事管理信息
### Request.json
{
personalinformation:人事信息
"transactorusername":办理人账号
}

### ResponseBody
{
""
}

 ## @RequestMapping("/updateCostInformation")
 修改人事成本信息
### Request.json
{
personalinformation:人事信息
"transactorusername":办理人账号
}

### ResponseBody
{
""
}

 ## @RequestMapping("/updateOtherInformation")
 修改人事其它信息
### Request.json
{
personalinformation:人事信息
"transactorusername":办理人账号
}

### ResponseBody
{
""
}

 ## @RequestMapping("/queryPersonalInformationsByDepid")
 根据depid查询人事信息
### Request.json
{
"depid": Integer 部门ID
}

### ResponseBody
{
[personalinformation]
}

 ## @RequestMapping("/queryPersonalInformationsByNull")
 查询所有人事信息（不分页）
### Request.json
{
}

### ResponseBody
{
[personalinformation]
}

 ## @RequestMapping("/importPersonalInformations")
 人事信息导入
### Request.json
{
"file":binary 文件
}

### ResponseBody
{
""
}

 ## @RequestMapping("/deleteInformationsByIds")
 根据IDs删除人事信息
### Request.json
{
"personalInformationIds": 人事信息ID数组
}

### ResponseBody
{
""
}

 ## @RequestMapping("/deleteAllInformations")
 删除人事所有信息（测试用）
### Request.json
{

}

### ResponseBody
{
}

 ## @RequestMapping("/queryGXF001")
 所有人员的工号、人名、部门（ID、名称）、岗位（ID、名称）（高晓飞）
### Request.json
{

}

### ResponseBody
{
    [
         {
             "employeeName": "",员工号
             "deptName": "",部门名称
             "post": [
                 {
                     "postName": "",岗位名称
                     "postId": "" 岗位ID
                 }
             ],
             "deptId": "",部门ID
             "id": "" 员工姓名
         }
    ]
}

 ## @RequestMapping("/queryGXF002")
 所有部门的ID、名称（高晓飞）
### Request.json
{
}

### ResponseBody
{
    [
        {
            "deptName":""部门名称
            "deptId":""部门ID
        }
    ]
}


 ## @RequestMapping("/queryGXF003")
 所有岗位的ID、名称（高晓飞）
### Request.json
{
}

### ResponseBody
{
    [
        {
            "postName":""岗位名称
            "postId":""岗位ID
        }
    ]
}

 ## @RequestMapping("/queryGXF004")
 根据部门ID查询部门信息(部门经理)（高晓飞）
### Request.json
{
"depid":部门ID
}

### ResponseBody
{
    String 部门经理名称
}

 ## @RequestMapping("/queryGXF005")
 查询所有的一级公司和二级公司（高晓飞）
### Request.json
{
}

### ResponseBody
{
    [
        "",公司名称
    ]
}

 ## @RequestMapping("/queryGXF006")
 根据公司名称查询其下属所有部门（高晓飞）
### Request.json
{
"depname":公司名称
}

### ResponseBody
{
    [String 部门名称]
}

 ## @RequestMapping("/queryGXF007")
 所有部门的ID、名称(公司级的提出)（高晓飞）
### Request.json
{

}

### ResponseBody
{
    [
        {
            "depName":部门名称
            "deptId":部门ID
        }
    ]
}

 ## @RequestMapping("/queryGXF008")
 根据人员名称查询所在公司（高晓飞）
### Request.json
{
"truename":员工姓名
}

### ResponseBody
{
""
}

 ## @RequestMapping("/queryHLT001")
 根据登录ID查询岗位名称
### Request.json
{
"username":账号
}

### ResponseBody
{
""
}

 ## @RequestMapping("/queryZHG001")
 赵宏钢的接口，根据账号查询信息
### Request.json
{
"username":账号
}

### ResponseBody
{
personalinformation
}

 ## @RequestMapping("/queryZHG002")
 根据岗位名称查询人员
### Request.json
{
"postname":岗位名称
}

### ResponseBody
{
    "head": {
        "rspCode": "205",
        "rspMsg": "请求成功！"
    },
    "body": [
        {
            "id": 2300,
            "username": "王力",
            "password": "123456",
            "truename": "王力",
            "isactive": 1,
            "state": 1,
            "employeenumber": "000007"
        }
    ]
}

 ## @RequestMapping("/queryWriteGzrz")
 根据日期查询工作日志（填写）
### Request.json
{
"year":年
"month":月
}

### ResponseBody
{
[工作日志对象]
}

 ## @RequestMapping("/queryApproveGzrz")
 根据日期查询工作日志（审查）
### Request.json
{
"year":年
"month":月
}

### ResponseBody
{
[工作日志对象]
}

 ## @RequestMapping("/queryAllLysqd")
 查询所有的录用申请单
### Request.json
{

}

### ResponseBody
{
[录用审批单对象]
}

#backend\src\main\java\com\elex\oa\controller\hr\PostInformationController.java:
post{
        "id": ,岗位ID
        "postname": "",岗位名称
        "functionaltype": "",职能
        "functionaltypeid": ,职能ID
        "postfamily": "",职系（已过时）
        "postfamilyid": ,职系ID（已过时）
        "postgrade": "",职等（已过时）
        "postgradeid": ,职等ID（已过时）
        "rank": ,职级
        "rankid": ,职级ID
        "postlevel": "",级别（已过时）
        "postlevelid": ,级别ID（已过时）
        "postcode": "",岗位编号
        "parentpostid": ,上级岗位ID
        "parentpost": {post},上级岗位
        "organization": "",编制
        "duty": "",职责
        "entryrequirements": "",入职需求
        "jobdescription": "",岗位概述
        "dutyfile": "",岗位说明书
        "state": ,启用状态
        "ordercode": 排序吗
    }
## @RequestMapping("/queryOnePostByPostname")
   根据岗位名称查询岗位信息
### Request.json
{
"postname":岗位名称
}

### ResponseBody
{
post
}

## @RequestMapping("/queryOnePostByPostid")
   根据岗位ID查询岗位信息
### Request.json
{
"id":岗位ID
}

### ResponseBody
{
post
}
xl
## @RequestMapping("/queryOnePostByPostcode")
  根据岗位编号查询岗位信息
### Request.json
{
"code":岗位编号
}

### ResponseBody
{
post
}

## @RequestMapping("/queryPosts")
  查询所有岗位信息
 ### Request.json
 {
 }
 
 ### ResponseBody
 {
    [post]
 }

## @RequestMapping("/queryPostsRemoveChilren")
  查询上级可选岗位信息
  ### Request.json
  {
  "postid":岗位ID
  }
  
  ### ResponseBody
  {
    [post]
  }

## @RequestMapping("/listPosts")
 ### Request.json
 {

 }
 
 ### ResponseBody
 {
    [depTree]
 }

## @RequestMapping("/addOnePost")
  添加岗位信息
### Request.json
{
post
}

### ResponseBody
{
""
}

## @RequestMapping("/queryByPostid")
  根据ID查询岗位信息
### Request.json
{
"id":岗位ID
}

### ResponseBody
{
post
}

## @RequestMapping("/updateOnePost")
  修改岗位信息
### Request.json
{
{post}，
"transactortruename":办理人账号
}

### ResponseBody
{
""
}

## @RequestMapping("/deletePostsById")
  根据ID级联删除岗位信息
### Request.json
{
"id":岗位ID
}

### ResponseBody
{
""
}

## @RequestMapping("/queryPostLogInformations")
  查询岗位日志列表信息（分页）
postlog {
               "id": ,岗位日志ID
               "postid": ,岗位ID
               "changeinformation": "",变更项目
               "beforeinformation": ,变更前内容
               "afterinformation": "",变更后内容
               "changereason": "",变更原因
               "changedate": "",变更日期
               "transactoruserid": ,变更人账号ID
               "postname": "",岗位名称
               "transactortruename": "",变更人姓名
           }
### Request.json
{
"page":"" 页码
"rows":"" 条数/每页
}

### ResponseBody
{
 "list":[postlog]
 "total":总条数
}

## @RequestMapping("/queryAllPostLogInformations")
  查询所有岗位信息（不分页）
### Request.json
{
}

### ResponseBody
{
[postlog]
}

## @RequestMapping("/deletePostlogByIds")
  根据ID删除岗位日志信息
### Request.json
{
"postlogids":[] 岗位日志ID数组
}

### ResponseBody
{
""
}

## @RequestMapping("/importPostloginformations")
  岗位日志信息导入
### Request.json
{
"file":binary 文件
}

### ResponseBody
{
""
}

## @RequestMapping("/sortPostinformation")
  查询岗位排序信息
### Request.json
{
"id":岗位ID
}

### ResponseBody
{
    [
        {
            "title": "",岗位名称
            "code": ,岗位排序码
            "id": 岗位ID
        }
    ]
}

## @RequestMapping("/submitSortdata2")
  提交岗位排序信息
### Request.json
{
"sortdata":{
               "title": "",岗位名称
               "code": ,岗位排序码
               "id": 岗位ID
            }
"title":岗位名称
"deptTree":DeptTree
}

### ResponseBody
{
    ""
}

#backend\src\main\java\com\elex\oa\controller\hr\PostRelationshipController.java:

## @RequestMapping("/postrelationship/addPostrelationship")
   添加岗级关系信息(已过时)
### Request.json
{

}

### ResponseBody
{
}

## @RequestMapping("/postrelationship/queryAllPostrelationship")
   查询所有的岗级关系信息（已过时）
### Request.json
{

}

### ResponseBody
{
}

## @RequestMapping("/postrelationship/removePostrelationship")
   删除岗级关系信息（已过时）
### Request.json
{

}

### ResponseBody
{
}

#backend\src\main\java\com\elex\oa\controller\hr\RAndPInformationController.java:

## @RequestMapping("/addRAndPInformation")
   添加奖惩信息（已过时）
### Request.json
{
}

### ResponseBody
{
}

## @RequestMapping("/queryRAndPProjects")
   查询奖惩信息（不分页）（已过时）
### Request.json
{
}

### ResponseBody
{
}

## @RequestMapping("/queryRAndPInformations")
   查询奖惩信息（分页）（已过时）
### Request.json
{
}

### ResponseBody
{
}

## @RequestMapping("/queryRAndPInformationById")
   根据ID查询奖惩信息（已过时）
### Request.json
{
}

### ResponseBody
{
}

## @RequestMapping("/updateRAndPInformationById")
  根据ID修改奖惩信息（已过时）
### Request.json
{
}

### ResponseBody
{
}

## @RequestMapping("/deleteRAndPInformationByIds")
  根据ID查询奖惩信息（已过时）
### Request.json
{
}

### ResponseBody
{
}

## @RequestMapping("/queryRAndPInformationByUserid")
根据账号ID查询奖惩信息（已过时）
### Request.json
{
}

### ResponseBody
{
}

#backend\src\main\java\com\elex\oa\controller\hr\UserController.java:
## @RequestMapping("/updateEmployeenumber")
   补充User表中employeenumber信息(不对外开放)
### Request.json
{
}

### ResponseBody
{
""
}

## @RequestMapping("/queryUserByUserId")
   根据ID查询员工账号
### Request.json
{
"userid":Integer require 员工账号ID
}

### ResponseBody
{
    user
}

## @RequestMapping("/queryAllUsers")
   查询所有的账号姓名
### Request.json
{
}

### ResponseBody
{
    [user]
}

## @RequestMapping("/queryAllServings")
   查询所有在职用户
### Request.json
{
}

### ResponseBody
{
    [user]
}

108 matches across 9 files
