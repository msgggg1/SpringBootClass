package org.doit.ik.mreview.dto;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadResultDTO  implements Serializable{    // p398
   
   private String fileName;   // 업로드된 원래 파일이름
   private String uuid;      // 파일의 UUID값
   private String folderPath;   // 업로드된 파일의 저장 경로
   
   public String getImageURL() {
      try {
         return URLEncoder.encode(folderPath+"/"+uuid+"_"+fileName, "UTF-8");
      } catch (UnsupportedEncodingException e) { 
         e.printStackTrace();
      }
      return "";
   }
    
   public String getThumbnailURL() {
      try {
         return URLEncoder.encode(folderPath+"/s_"+uuid+"_"+fileName, "UTF-8");
      } catch (UnsupportedEncodingException e) { 
         e.printStackTrace();
      }
      return "";
   }

}
