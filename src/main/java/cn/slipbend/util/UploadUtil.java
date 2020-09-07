package cn.slipbend.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: AnZX
 * @Date: 2020/07/30/13:50
 * @Description: 图片上传工具类
 */
public class UploadUtil {

    public static Object upload(MultipartFile file,String path){
        Map<String, Object> map =new HashMap<>();
        //获取上传的图片名
        String fileName = file.getOriginalFilename();
        //生成新名称
        String newFileName = UUID.randomUUID().toString().replace("-", "") + fileName.substring(fileName.lastIndexOf("."));
        //上传到服务器
        File dest = new File(path+newFileName);
        //判断文件父目录是否存在
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        //保存文件
        try {
            file.transferTo(dest);
            map.put("msg","上传成功");
            map.put("imageUrl",newFileName);//图片回显路径
            map.put("fileName",newFileName);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("msg","上传失败");
        }
        return map;
    }
}

