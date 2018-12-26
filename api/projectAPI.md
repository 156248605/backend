
# backend\src\main\java\com\elex\oa\controller\project\ListingController.java:
   14  @Controller
   15  @CrossOrigin
## @RequestMapping("/listing")
   17  public class ListingController {
   18      @Autowired
   ..
   20  
   21      //审批清单列表查询
## @RequestMapping("/list_query")
   23      @ResponseBody
   24      public PageInfo<ApprovalList> listQuery(Page page, AListQuery aListQuery) {

# backend\src\main\java\com\elex\oa\controller\project\MileStoneController.java:
   18  @Controller
   19  @CrossOrigin
## @RequestMapping("/mileStone")
   21  public class MileStoneController {
   22  
   ..
   25  
   26      //列表查询里程碑计划
## @RequestMapping("/query_list")
   28      @ResponseBody
   29      public PageInfo queryList(AListQuery aListQuery, Page page) {
   ..
   32  
   33      //查询可新建里程碑计划的项目信息
## @RequestMapping("/query_project_list")
   35      @ResponseBody
   36      public PageInfo queryProjectList(OperationQuery operationQuery, int currentPage) {
   ..
   38      }
   39      //添加里程碑计划
## @RequestMapping("/add_plans")
   41      @ResponseBody
   42      public String addPlans(String dataS, MileStone mileStone) {
   ..
   45  
   46      //查询里程碑计划详情
## @RequestMapping("/query_plan_code")
   48      @ResponseBody
   49      public List<MileStonePlan> queryPlansCode(String projectCode) {
   ..
   52  
   53      //修改里程碑计划详情
## @RequestMapping("/modify_plans_code")
   55      @ResponseBody
   56      public String modifyPlansCode(String dataS, MileStone mileStone) {

# backend\src\main\java\com\elex\oa\controller\project\NewProjectController.java:
   16  @Controller
   17  @CrossOrigin
## @RequestMapping("/newProject")
   19  public class NewProjectController {
   20      //项目管理，立项相关
   ..
   27  
   28      //对某人已建项目进行列表查询
## @RequestMapping("/list_query")
   30      @ResponseBody
   31      public PageInfo<NewProject> listQuery(Page page, NProjectQuery nProjectQuery){
   ..
   34  
   35      //获取表头部分select中的内容
## @RequestMapping("/select_data")
   37      @ResponseBody
   38      public List<NewProject> selectData(String proposer) {
   ..
   41  
   42      //根据人名获取项目编号
## @RequestMapping("/obtain_code_name")
   44      @ResponseBody
   45      public String obtainCodeName(String name) {

# backend\src\main\java\com\elex\oa\controller\project\OperationController.java:
   15  @Controller
   16  @CrossOrigin
## @RequestMapping("/operate")
   18  public class OperationController {
   19  
   ..
   22  
   23      //列表查询主表数据
## @RequestMapping("/query_main_list")
   25      @ResponseBody
   26      public PageInfo queryMainList (OperationQuery operationQuery, Page page) {
   ..
   29  
   30      //列表查询当前可新建的项目
## @RequestMapping("/query_project_list")
   32      @ResponseBody
   33      public PageInfo queryProjectList(OperationQuery operationQuery, Page page) {
   ..
   36  
   37      //列表查询物品消耗
## @RequestMapping("/query_material_list")
   39      @ResponseBody
   40      public PageInfo queryMaterialList(OperationQuery operationQuery, Page page) {
   ..
   43  
   44      //查询物品消耗详情
## @RequestMapping("/query_material_detail")
   46      @ResponseBody
   47      public Map<String,Object> queryMaterialDetail(String projectCode) {
   ..
   50  
   51      //添加人力成本
## @RequestMapping("/add_human")
   53      @ResponseBody
   54      public String addHuman(ProjectHuman projectHuman, String detail) {
   ..
   57  
   58      //查询人力成本信息
## @RequestMapping("/query_human_detail")
   60      @ResponseBody
   61      public Map<String,Object> queryHumanDetail (String projectCode) {
   ..
   64  
   65      //修改人力成本
## @RequestMapping("/modify_human")
   67      @ResponseBody
   68      public String modifyHuman(ProjectHuman projectHuman, String detail) {
   ..
   71  
   72      //添加费用报销
## @RequestMapping("/add_expense")
   74      @ResponseBody
   75      public String addExpense(ProjectExpense projectExpense, String detail) {
   ..
   78  
   79      //查询费用报销信息
## @RequestMapping("/query_expense_detail")
   81      @ResponseBody
   82      public Map<String,Object> queryExpenseDetail (String projectCode) {
   ..
   85  
   86      //修改费用报销
## @RequestMapping("/modify_expense")
   88      @ResponseBody
   89      public String modifyExpense(ProjectExpense projectExpense, String detail) {
   ..
   92  
   93      //查询项目收入相关可新建的项目
## @RequestMapping("/query_project_income")
   95      @ResponseBody
   96      public PageInfo<ProjectInfor> queryProjectIncome(OperationQuery operationQuery, Page page) {
   ..
   99  
  100      //添加新的项目收入
## @RequestMapping("/insert_income")
  102      @ResponseBody
  103      public String insertIncome(ProjectIncome projectIncome, String contract1, String contract2) {
  ...
  106  
  107      //查询项目收入列表
## @RequestMapping("/query_income_list")
  109      @ResponseBody
  110      public PageInfo<ProjectInfor> queryIncomeList(OperationQuery operationQuery, Page page) {
  ...
  113  
  114      //查询项目收入的内容
## @RequestMapping("/query_income_content")
  116      @ResponseBody
  117      public Map<String,Object> queryIncomeContent(String projectCode) {
  ...
  120  
  121      //更新项目收入的内容
## @RequestMapping("/update_income")
  123      @ResponseBody
  124      public String updateIncome(ProjectIncome projectIncome, String contract1, String contract2) {
  ...
  127  
  128      //查询人力成本相关可新建的项目
## @RequestMapping("/query_project_human")
  130      @ResponseBody
  131      public PageInfo<ProjectInfor> queryProjectHuman(OperationQuery operationQuery, Page page) {
  ...
  134  
  135      //查询人力成本列表
## @RequestMapping("/query_human_list")
  137      @ResponseBody
  138      public PageInfo<ProjectInfor> queryHumanList(OperationQuery operationQuery, Page page) {
  ...
  141  
  142      //查询费用成本列表
## @RequestMapping("/query_expense_list")
  144      @ResponseBody
  145      public PageInfo<ProjectInfor> queryExpenseList(OperationQuery operationQuery, Page page) {
  ...
  148  
  149      //查询费用成本相关可新建的项目
## @RequestMapping("/query_project_expense")
  151      @ResponseBody
  152      public PageInfo<ProjectInfor> queryProjectExpense(OperationQuery operationQuery, Page page) {

# backend\src\main\java\com\elex\oa\controller\project\ProjectBoardController.java:
   13  import java.util.Map;
   14  
## @RequestMapping("/board")
   16  @CrossOrigin
   17  @Controller
   ..
   22  
   23      //列表总览
## @RequestMapping("/overview")
   25      @ResponseBody
   26      public Map<String,Object> overview(String department) {
   ..
   29  
   30      //详情
## @RequestMapping("/detail")
   32      @ResponseBody
   33      public Map<String,Object> detail(String projectCode) {
   ..
   37  
   38      //看板手机部门相关详情（手机）
## @RequestMapping("/project_total")
   40      @ResponseBody
   41      List<Map<String, String>> projectTotal(String department){
   ..
   44  
   45      //看板根据类型查询概况（手机）
## @RequestMapping("/project_survey")
   47      @ResponseBody
   48      List<Map<String, String>> projectSurvey(String status, String type, String department){
   ..
   51  
   52      //查看某一类型的项目
## @RequestMapping("/project_various")
   54      @ResponseBody
   55      public PageInfo projectVarious(Integer pageNum, String status, String type, String department){
   ..
   58  
   59      //跟新人员信息
## @RequestMapping("/update_staff")
   61      @ResponseBody
   62      public String updateStaff() {
   ..
   66  
   67      //查询所有人员
## @RequestMapping("/query_staff")
   69      @ResponseBody
   70      public List<Staff> queryStaff() {
   ..
   73  
   74      //查看某类型的项目
## @RequestMapping("/project_status")
   76      @ResponseBody
   77      public PageInfo peojectStatus(Integer pageNum, String status, String type, String department) {
   ..
   80  
   81      //查看某阶段的项目
## @RequestMapping("/project_phase")
   83      @ResponseBody
   84      public PageInfo peojectPhase(Integer pageNum, String phase, String type, String department) {
   ..
   87  
   88      //查看是否延期的项目
## @RequestMapping("/project_week")
   90      @ResponseBody
   91      public PageInfo projectWeek(Integer pageNum, String punctuality, String type, String department) {
   ..
   94  
   95      //查看进行中项目的类型及数量(手机端)
## @RequestMapping("/query_proceed")
   97      @ResponseBody
   98      public List<Object> queryProceed(String department) {
   ..
  101  
  102      //点击进行中项目的数量查看相关详情（手机端）
## @RequestMapping("/query_week")
  104      @ResponseBody
  105      public List<Map<String,String>> queryWeek(String department, String type, String punctuality) {

# backend\src\main\java\com\elex\oa\controller\project\ProjectInforController.java:
   13  @Controller
   14  @CrossOrigin
## @RequestMapping("/proInfor")
   16  public class ProjectInforController {
   17  
   ..
   21  
   22      //列表查询项目详情信息
## @RequestMapping("/query_list")
   24      @ResponseBody
   25      public PageInfo queryList(OperationQuery operationQuery, Integer pageNum) {
   ..
   28  
   29      //修改项目信息
## @RequestMapping("/amend_infor")
   31      @ResponseBody
   32      public String amendInfor(ProjectInfor projectInfor) {
   ..
   35  
   36      //添加新项目
## @RequestMapping("add_infor")
   38      @ResponseBody
   39      public String addInfor(){

# backend\src\main\java\com\elex\oa\controller\project\ProjectSetController.java:
   11  import java.util.List;
   12  
## @RequestMapping("/hammer")
   14  @Controller
   15  @CrossOrigin
   ..
   20  
   21      //查询所有项目类型
## @RequestMapping("/query_type")
   23      @ResponseBody
   24      public List<ProjectVarious> queryType() {
   ..
   27  
   28      //查询所有项目来源
## @RequestMapping("/query_source")
   30      @ResponseBody
   31      public List<ProjectVarious> querySource() {
   ..
   34  
   35      //查询所有项目状态
## @RequestMapping("/query_status")
   37      @ResponseBody
   38      public List<ProjectVarious> queryStatus() {
   ..
   41  
   42      //查询所有项目阶段
## @RequestMapping("/query_phase")
   44      @ResponseBody
   45      public List<ProjectVarious> queryPhase() {

# backend\src\main\java\com\elex\oa\controller\project\WeeklyPlanController.java:
   16  @Controller
   17  @CrossOrigin
## @RequestMapping("/weeklyPlan")
   19  public class WeeklyPlanController {
   20      @Autowired
   ..
   22  
   23      //数据列表查询
## @RequestMapping("/query_list")
   25      @ResponseBody
   26      public PageInfo queryList(WeeklyPlanQuery weeklyPlanQuery, Page page) {
   ..
   29  
   30      //根据关联id查询计划
## @RequestMapping("/query_plan_id")
   32      @ResponseBody
   33      public List<WeeklyPlan> queryPlansById(String related) {
   ..
   36  
   37      //根据姓名查询项目编号
## @RequestMapping("/query_code_name")
   39      @ResponseBody
   40      public PageInfo<ApprovalList> queryCodeByName(String name, Page page) {
   ..
   43  
   44      //根据code 查询详情
## @RequestMapping("/query_detail_code")
   46      @ResponseBody
   47      public Map<String,String> queryDetailByCode(String code) {
   ..
   50  
   51      //添加周计划
## @RequestMapping("/add_plans")
   53      @ResponseBody
   54      public String addPlans(WeeklyPlan weeklyPlan) {
   ..
   57  
   58      //审批周计划
## @RequestMapping("/approval_plans")
   60      @ResponseBody
   61      public String approvalPlans(WeeklyPlan weeklyPlan) {
   ..
   64  
   65      //查询项目信息
## @RequestMapping("/query_project_name")
   67      @ResponseBody
   68      public PageInfo<ProjectInfor> queryProjectName(OperationQuery operationQuery, Page page) {
   ..
   71  
   72      //修改周计划
## @RequestMapping("/amend_plans")
   74      @ResponseBody
   75      public String amendPlans(WeeklyPlan weeklyPlan) {
   ..
   78  
   79      //删除周计划
## @RequestMapping("/delete_week")
   81      @ResponseBody
   82      public String deleteWeek(int id) {
   ..
   85  
   86      //查询某项目的里程碑计划
## @RequestMapping("/query_mileStone")
   88      @ResponseBody
   89      public List<String> queryMileStone(String code) {

65 matches across 8 files
