package com.logicify.htb.configurator.web.controller;

import com.logicify.htb.configurator.web.common.AbstractJsonController;
import com.logicify.htb.configurator.web.common.AjaxResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p/>
 * User: Dmitry Berezovsky (LOGICIFY\corvis)
 * Date: 11/21/13
 * Time: 1:43 PM
 */
@Controller
@RequestMapping("/api/ping.json")
public class HomePageController extends AbstractJsonController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public AjaxResponse ping() {
        return AjaxResponse.success();
    }
}
