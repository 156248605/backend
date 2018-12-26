# backend\src\main\java\com\elex\oa\controller\hr\ChangeInformationController.java:

## @RequestMapping("/queryChangeInformations")
获得变更信息的列表信息（分页）
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryAllChangeInformations")
获得变更信息的列表信息（不分页）
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/deleteChangeinformationByIds")
根据IDs删除变更信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/importChangeinformations")
变更信息的导入
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

#backend\src\main\java\com\elex\oa\controller\hr\ContractInformationController.java:

## @RequestMapping("/queryContractInformations")
   获得合同列表信息（分页）
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryContractsByUserid")
   根据userid获得合同信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryOneContractInformation")
  根据合同ID获得合同续签信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryContractsById")
  根据合同ID获得合同信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/addContractInformation")
  添加一条合同信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/updateContractInformation")
  更新一条合同信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryAllContractInformations")
  查询所有的合同信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/deleteContractsByIds")
  根据IDs删除合同信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/importContractinformations")
  合同信息的导入
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

#backend\src\main\java\com\elex\oa\controller\hr\DepartmentInformationController.java:

## @RequestMapping("/queryOneDepByDepname")
   根据部门名称查询部门信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryOneDepByDepcode")
  根据部门编号查询部门信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryDepartments")
  查询所有的部门信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryDepartmentsRemoveChilren")
  根据depid查询可选的上级部门
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/listDepts")
  查询部门树信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/addOneDepartment")
  添加一条部门信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryOneByDepid")
  根据depid查询一条部门信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/updateOneDepartment")
  更新部门信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/deleteDeptsById")
  根据depid级联删除部门信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryDeptLogInformations")
  查询部门日志列表信息（分页）
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/queryAllDeptLogInformations")
 查询所有的部门日志信息（不分页）
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/deleteDeplogByIds")
 根据IDs删除部门日志信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/importDeploginformations")
 部门日志信息导入
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/sortDepinformation")
 部门排序--》根据父节点查询所有的同级数据
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/submitSortdata")
 对数据进行排序
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/queryHRManageCard")
 根据初始时间、公司名称查询管理看板信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/queryHRManageCard2")
 获得总人数(edate时间点的在职总人数)
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/queryHRManageCard3")
 获得入职总人数(edate时间点的入职总人数)
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/queryHRManageCard4")
 获得离职总人数(edate时间点的离职总人数)
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/queryHRManageCard5")
 获得所在部门在职人数(edate时间点的在职总人数)(注意转部门员工的影响，注：此种情况前期版本暂不考虑)
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/queryHRManageCard6")
 获得所在部门入职人数(edate时间点的入职总人数)(注意转部门员工的影响，注：此种情况前期版本暂不考虑)
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/queryHRManageCard7")
 获得所在部门离职人数(edate时间点的入职总人数)(注意转部门员工的影响，注：此种情况前期版本暂不考虑)
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/queryIOSData")
 IOS的通讯录数据（根据depid查询人事概述信息：姓名）
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/validateDeptnameForAddDept")
 添加部门的时候校验部门名称是否存在
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/validateDeptcodeForAddDept")
 添加部门的时候校验部门编号
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

#backend\src\main\java\com\elex\oa\controller\hr\DimissionController.java:

## @RequestMapping("/addDimission")
   添加离职信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryDimissionInformations")
   查询离职列表信息（分页）
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryAllDimissionInformations")
  查询所有的离职信息（不分页）
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/modifyDimissionInformationById")
  根据ID修改离职信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryDimissionInformationById")
  根据ID查询离职信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/removeDimissionInformations")
  根据ID删除离职信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/importDimissionInformations")
  离职信息导入
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

#backend\src\main\java\com\elex\oa\controller\hr\PersonalInformationController.java:

## @RequestMapping("/PersonalInformationController/queryPersonalInformations")
   人事列表信息展示（数据初始化）
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryPersonalInformations")
   人事信息查询（分页）
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryPersonalInformationById")
  根据ID查询人事信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryPersonalInformationByUserid")
  根据userid查询人事信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryPersonalInformationByTruename")
  根据姓名查询人事信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping(value = "/addBaseInformation", consumes = "multipart/form-data")
  添加人事基础信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/addManageInformation")
  添加人事管理信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/addCostInformation")
  添加人事成本信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/addOtherInformation")
  添加人事其它信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/updateBaseInformation")
  修改人事基础信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/updateManageInformation")
 修改人事管理信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/updateCostInformation")
 修改人事成本信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/updateOtherInformation")
 修改人事其它信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/queryPersonalInformationsByDepid")
 根据depid查询人事信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/queryPersonalInformationsByNull")
 查询所有人事信息（不分页）
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/importPersonalInformations")
 人事信息导入
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/deleteInformationsByIds")
 根据IDs删除人事信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/deleteAllInformations")
 删除人事所有信息（测试用）
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/queryGXF001")
 所有人员的工号、人名、部门（ID、名称）、岗位（ID、名称）（高晓飞）
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/queryGXF002")
 所有部门的ID、名称（高晓飞）
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/queryGXF003")
 所有岗位的ID、名称（高晓飞）
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/queryGXF004")
 根据部门ID查询部门信息(部门经理)（高晓飞）
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/queryGXF005")
 查询所有的一级公司和二级公司（高晓飞）
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/queryGXF006")
 根据公司名称查询其下属所有部门（高晓飞）
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/queryGXF007")
 所有部门的ID、名称(公司级的提出)（高晓飞）
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/queryGXF008")
 根据人员名称查询所在公司（高晓飞）
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/queryHLT001")
 根据登录ID查询岗位名称
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/queryZHG001")
 赵宏钢的接口，根据账号查询信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/queryZHG002")
 根据岗位名称查询人员
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/queryWriteGzrz")
 根据日期查询工作日志（填写）
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/queryApproveGzrz")
 根据日期查询工作日志（审查）
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

 ## @RequestMapping("/queryAllLysqd")
 查询所有的录用申请单
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

#backend\src\main\java\com\elex\oa\controller\hr\PostInformationController.java:

## @RequestMapping("/queryOnePostByPostname")
   根据岗位名称查询岗位信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryOnePostByPostid")
   根据岗位编号查询岗位信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}
xl
## @RequestMapping("/queryOnePostByPostcode")
  根据岗位编号查询岗位信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryPosts")
  查询所有岗位信息
 ### Request.json
 {
 id:  string,  require/ 
 depart: option
 }
 
 ### ResponseBody
 {
 }

## @RequestMapping("/queryPostsRemoveChilren")
  查询上级可选岗位信息
  ### Request.json
  {
  id:  string,  require/ 
  depart: option
  }
  
  ### ResponseBody
  {
  }

## @RequestMapping("/listPosts")
 ### Request.json
 {
 id:  string,  require/ 
 depart: option
 }
 
 ### ResponseBody
 {
 }

## @RequestMapping("/addOnePost")
  添加岗位信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryByPostid")
  根据ID查询岗位信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/updateOnePost")
  修改岗位信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/deletePostsById")
  根据ID级联删除岗位信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryPostLogInformations")
  查询岗位日志列表信息（分页）
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryAllPostLogInformations")
  查询所有岗位信息（不分页）
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/deletePostlogByIds")
  根据ID删除岗位日志信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/importPostloginformations")
  岗位日志信息导入
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/sortPostinformation")
  查询岗位排序信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/submitSortdata2")
  提交岗位排序信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

#backend\src\main\java\com\elex\oa\controller\hr\PostRelationshipController.java:

## @RequestMapping("/postrelationship")
   查询岗级关系信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/addPostrelationship")
   添加岗级关系信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryAllPostrelationship")
   查询所有的岗级关系信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/removePostrelationship")
   删除岗级关系信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

#backend\src\main\java\com\elex\oa\controller\hr\RAndPInformationController.java:

## @RequestMapping("/addRAndPInformation")
   添加奖惩信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryRAndPProjects")
   查询奖惩信息（不分页）
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryRAndPInformations")
   查询奖惩信息（分页）
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryRAndPInformationById")
   根据ID查询奖惩信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/updateRAndPInformationById")
  根据ID修改奖惩信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/deleteRAndPInformationByIds")
  根据ID查询奖惩信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryRAndPInformationByUserid")
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

#backend\src\main\java\com\elex\oa\controller\hr\UserController.java:
   41       **/
   42  
## @RequestMapping("/updateEmployeenumber")
   补充User表中employeenumber信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryUserByUserId")
   根据ID查询员工信息
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryAllUsers")
   查询所有的账号姓名
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

## @RequestMapping("/queryAllServings")
   查询所有在职用户
### Request.json
{
id:  string,  require/ 
depart: option
}

### ResponseBody
{
}

108 matches across 9 files
