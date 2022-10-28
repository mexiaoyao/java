package com.youlianmei.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlianmei.dao.FinanceIntroDao;
import com.youlianmei.model.FinanceIntro;
import com.youlianmei.model.Shares;
import com.youlianmei.service.FinanceIntroService;
import com.youlianmei.service.SharesService;
import com.youlianmei.utils.FileUploadUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //@RestController相当于@Controller和@ResponseBody合在一起的作用；
@RequestMapping(value = "shares")
public class SharesController {

    @Resource
    SharesService sharesService;

    @PostMapping(value = "/upload")
    public Object upload(@RequestParam("file") MultipartFile fileName) {
        Map<String,Object> obj = new HashMap<>();
        if(FileUploadUtils.isEmpty(fileName)){
            obj.put("code","20000");
            obj.put("msg","空文件");
            return obj;
        }
        if(!FileUploadUtils.isExcel(fileName)){
            obj.put("code","20000");
            obj.put("msg","只支持EXCEL文件");
            return obj;
        }
        try {
            List<Shares> sharesList = EasyExcel.read(fileName.getInputStream())
                    .head(Shares.class)
                    .sheet()
                    .doReadSync();
            obj.put("code","10000");
        }catch(Exception e){
            obj.put("code","20000");
            obj.put("msg","导入失败");
        }
        return obj;
    }

    /**
     * 股票列表
     * **/
    @PostMapping("list")
    public Object list(@RequestBody FinanceIntroDao financeIntro) throws Exception {
        Map<String,Object> obj = new HashMap<>();

        obj.put("code","10000");

        return obj;
    }


}
