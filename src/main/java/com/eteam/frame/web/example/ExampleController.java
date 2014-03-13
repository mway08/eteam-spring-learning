package com.eteam.frame.web.example;

import com.eteam.frame.service.example.ExampleServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/** 
 * 框架实例控制器
 * <p>Date : 2013-3-13</p>
 * <p>Module : 使用用例</p>
 * <p>Copyright: Copyright &copy; 2013</p>
 * <p>Company : eteam</p>
 * 
 * @author liufm, BD&PRC
 * @version frame V1.0
 * <p>------------------------------------------------------------</p>
 * <p> 修改历史</p>
 * <p> 序号 日期 修改人 修改原因</p>
 * <p> 1 </p>
 */
@Controller
public class ExampleController {
	
	static final Logger logger = LoggerFactory.getLogger(ExampleController.class);
	
	@Autowired
    ExampleServiceImpl exampleService;

    @RequestMapping("example/test")
    @ResponseBody
    public ModelMap doTest() {
        ModelMap modelMap = new ModelMap();
        try {
            exampleService.doInsert();
            modelMap.put("iRtn", 1);
        } catch (Exception e) {
            System.out.println(e);
            modelMap.put("iRtn", "0");
        }
        return modelMap;
    }

}
