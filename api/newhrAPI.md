
# backend\src\main\java\com\elex\oa\controller\restructure_hr\DepartmentInfoController.java:
   28  @Controller
   29  @CrossOrigin
## @RequestMapping("/depinfo")
   31  public class DepartmentInfoController {
   32      @Autowired
   ..
   35      IDeploginfoService iDeploginfoService;
   36  
## @RequestMapping("/listDepts")
   38      @ResponseBody
   39      public Map<String,Object> listDepts(){
   ..
   41      }
   42  
## @RequestMapping("/queryOneDepByDepcode")
   44      @ResponseBody
   45      public Depinfo queryOneDepByDepcode(
   ..
   49      }
   50  
## @RequestMapping("/addOneDepartment")
   52      @ResponseBody
   53      public Object addOneDepartment(
   ..
   59      }
   60  
## @RequestMapping("/queryDepartments")
   62      @ResponseBody
   63      public List<Depinfo> queryDepartments(){
   ..
   65      }
   66  
## @RequestMapping("/queryDepartmentsRemoveChilren")
   68      @ResponseBody
   69      public List<Map<String,String>> queryDepartmentsRemoveChilren(
   ..
   73      }
   74  
## @RequestMapping("/updateOneDepartment")
   76      @ResponseBody
   77      public Object updateOneDepartment(
   ..
   84      }
   85  
## @RequestMapping("/sortDepinformation")
   87      @ResponseBody
   88      public List<Map<String,Object>> sortDepinformation(
   ..
   92      }
   93  
## @RequestMapping("/submitSortdata")
   95      @ResponseBody
   96      public Map<String,Object> submitSortdata(
   ..
  102      }
  103  
## @RequestMapping("/deleteDeptsByDepcode")
  105      @ResponseBody
  106      public Object deleteDeptsByDepcode(
  ...
  111      }
  112  
## @RequestMapping("/queryDeptLogInformations")
  114      @ResponseBody
  115      public PageInfo<Deploginfo> queryDeptLogInformations(
  ...
  121      }
  122  
## @RequestMapping("/queryAllDeptLogInformations")
  124      @ResponseBody
  125      public List<Deploginfo> queryAllDeptLogInformations(){
  ...
  127      }
  128  
## @RequestMapping("/importDeploginformations")
  130      @ResponseBody
  131      public Object importDeploginformations(

# backend\src\main\java\com\elex\oa\controller\restructure_hr\HRsetController.java:
   25  @CrossOrigin
   26  @Controller
## @RequestMapping("/hrset")
   28  public class HRsetController {
   29      @Autowired
   ..
   40       *@Date: 18:43 2018\5\11 0011
   41       */
## @RequestMapping("/addOneHRset")
   43      @ResponseBody
   44      public String addOneHRset(
   ..
   54       *@Date: 18:45 2018\5\11 0011
   55       */
## @RequestMapping("/queryAllHRset")
   57      @ResponseBody
   58      public List<Hrdatadictionary> queryAllHRset(
   ..
   69       * @return
   70       **/
## @RequestMapping("/queryAllHRsetByNull")
   72      @ResponseBody
   73      public List<Hrdatadictionary> queryAllHRsetByNull(){
   ..
   81       *@Date: 11:32 2018\5\19 0019
   82       */
## @RequestMapping("/queryHRsetPageInfo")
   84      @ResponseBody
   85      public PageInfo<Hrdatadictionary> queryHRPageInfo(
   ..
   98       * @return java.lang.Boolean
   99       **/
## @RequestMapping("/queryValidateHRset")
  101      @ResponseBody
  102      public Boolean queryValidateHRset(
  ...
  113       * @return java.lang.String
  114       **/
## @RequestMapping("/removeHRset")
  116      @ResponseBody
  117      public Map<String,String> removeHRset(
  ...
  128       * @return java.lang.String
  129       **/
## @RequestMapping("/modifyHRset")
  131      @ResponseBody
  132      public String modifyHRset(
  ...
  144       * @return
  145       **/
## @RequestMapping("/queryPostgradeByPostfamilyid")
  147      @ResponseBody
  148      public List<Hrdatadictionary> queryPostgradeByPostfamilyid(

# backend\src\main\java\com\elex\oa\controller\restructure_hr\PersonalinfoController.java:
   20  @Controller
   21  @CrossOrigin
## @RequestMapping("/personalinfo")
   23  public class PersonalinfoController {
   24      @Autowired
   25      IPersonalinfoService iPersonalinfoService;
   26  
## @RequestMapping("/queryAllUsers")
   28      @ResponseBody
   29      public List<Map<String,String>> queryAllUsers(){

# backend\src\main\java\com\elex\oa\controller\restructure_hr\PostInfoController.java:
   30  @Controller
   31  @CrossOrigin
## @RequestMapping("/postinfo")
   33  public class PostInfoController {
   34      @Autowired
   ..
   37      HrUtilsTemp hrUtilsTemp;
   38  
## @RequestMapping("/listPosts")
   40      @ResponseBody
   41      public Map<String,Object> listPosts(){
   ..
   43      }
   44  
## @RequestMapping("/queryOnePostByPostcode")
   46      @ResponseBody
   47      public Postinfo queryOnePostByPostcode(
   ..
   51      }
   52  
## @RequestMapping("/queryPosts")
   54      @ResponseBody
   55      public List<Postinfo> queryPosts(){
   ..
   57      }
   58  
## @RequestMapping("/validateByPostcode")
   60      @ResponseBody
   61      public Object validateByPostcode(
   ..
   66      }
   67  
## @RequestMapping("/addOnePost")
   69      @ResponseBody
   70      public Object addOnePost(

# backend\src\main\java\com\elex\oa\controller\restructure_hr\TablesController.java:
   16  @CrossOrigin
   17  @Controller
## @RequestMapping("/changetable")
   19  public class TablesController {
   20      @Autowired
   ..
   33      IPostandpersonalrelationshipService iPostandpersonalrelationshipService;
   34  
## @RequestMapping("/tb_hr_data_dictionary")
   36      @ResponseBody
   37      public String changetable_tb_hr_data_dictionary(){
   ..
   40      }
   41  
## @RequestMapping("/tb_hr_contract_info")
   43      @ResponseBody
   44      public String changetable_tb_hr_contract_info(){
   ..
   47      }
   48  
## @RequestMapping("/tb_id_dep_info")
   50      @ResponseBody
   51      public String changetable_tb_id_dep_info(){
   ..
   54      }
   55  
## @RequestMapping("/tb_id_post_info")
   57      @ResponseBody
   58      public String changetable_tb_id_post_info(){
   ..
   61      }
   62  
## @RequestMapping("/tb_id_post_info/updateNodelevel")
   64      @ResponseBody
   65      public String changetable_tb_id_post_info_updateNodelevel(){
   ..
   68      }
   69  
## @RequestMapping("/tb_postlevel_relationship_info")
   71      @ResponseBody
   72      public String changetable_tb_postlevel_relationship_info(){
   ..
   75      }
   76  
## @RequestMapping("/tb_id_personal_info")
   78      @ResponseBody
   79      public String changetable_tb_id_personal_info(){
   ..
   82      }
   83  
## @RequestMapping("/tb_postandpersonal_relationship_info")
   85      @ResponseBody
   86      public String changetable_tb_postandpersonal_relationship_info(){

39 matches across 5 files
