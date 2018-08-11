package com.martin.controller;

import com.martin.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: qienbo
 * @Date: 2018/8/11 下午1:44
 * @Description:
 */
@Controller
public class IndexController extends BaseController {
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String go2Index(){
        return "index1";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "redirect:/index";
    }
}
