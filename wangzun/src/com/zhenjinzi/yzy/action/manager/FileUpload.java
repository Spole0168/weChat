package com.zhenjinzi.yzy.action.manager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import com.fire.modules.web.struts2.Struts2Utils;
import com.zhenjinzi.util.RandomController;
import com.zhenjinzi.yzy.action.BaseAction;

public class FileUpload extends BaseAction {

	private static final long serialVersionUID = 1L;
	private File uploadify;
	private String fileName;

	public String uploadNewsImage() {
		if (uploadify != null) {
			fileName = request.getParameter("Filename");
			String savePath = httpSession.getServletContext().getRealPath("");
			SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMM");
			String folder = "/uploads/news/"+dtFormat.format(new Date());
			savePath = savePath + folder;
			//fileName=uploadify.getName();
			String fileExt = fileName.substring(fileName.lastIndexOf("."));
			fileName = RandomController.getCharacterAndNumber(16)+fileExt;
			
			File savefile = new File(new File(savePath), fileName);
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			try {
				FileUtils.copyFile(uploadify, savefile);
				Struts2Utils.renderJson("{\"status\":true,\"error\":\"abc\",\"data\":{\"filePath\":\""+folder+"/"+fileName+"\"}}");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	public File getUploadify() {
		return uploadify;
	}

	public void setUploadify(File uploadify) {
		this.uploadify = uploadify;
	}
}
