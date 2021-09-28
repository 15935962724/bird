package com.bird.controller.admin;

import com.bird.entity.Area;
import com.bird.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("adminArea")
@RequestMapping("/area")
public class AreaController {


	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AreaService areaService;


    /**
     * 三级联动插件
     * @param parentId
     * @return
     */
    @RequestMapping("/getAreas")
    @ResponseBody
    public Map<Long, String>  getAreas( @RequestParam(defaultValue = "0") Long parentId){
        //类别
        Map query_map = new HashMap();
        query_map.put("parent",parentId);
        List<Area> areas= areaService.getAreas(query_map);
        Map<Long, String> options = new HashMap<Long, String>();
        for (Area area : areas) {
            options.put(area.getId(), area.getName());
        }
        return options;
    }


    /**
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
    @ResponseBody
	public String list(Model model) {
		List<Area> areas = areaService.getAreas(null);
		for (Area area : areas) {
            String tree_path = areaService.getTree_path(area.getId());
            System.out.println(">>>>>>>>>"+tree_path);
            String tree_path_new = ","+tree_path+","+area.getParent()+",";
            area.setTreePath(tree_path_new);
            areaService.updateByPrimaryKeySelective(area);
		}
		return "admin/member/list";
	}




    /**
     *
     * @param model
     * @return
     */
    @RequestMapping("/view")
    @ResponseBody
    public String view(Model model,Long id) {
        String tree_path = areaService.getTree_path(id);
        System.out.println(">>>>>>>>>"+tree_path);
        return tree_path;
    }





}
