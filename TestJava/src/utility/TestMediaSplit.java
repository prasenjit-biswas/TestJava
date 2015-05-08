package utility;

import org.apache.commons.lang3.StringUtils;

public class TestMediaSplit {
	public static void main(String[] args) {
		String oldName = "abc.tlt.mov";
		String useName = "5abc.ext";
		if(StringUtils.isNotBlank(oldName) && useName.endsWith(".ext")){
			String newOldName = "";
			String oldNameExt = "";
			String[] oldNameArr = oldName.split("\\.");
			if(oldNameArr != null && oldNameArr.length == 2){
				oldNameExt = oldNameArr[1];
			}
			String useNameWithoutExt = "";
			String[] useNameArr = useName.split("\\.");
			if(useNameArr != null && useNameArr.length == 2){
				useNameWithoutExt = useNameArr[0];
			}
			if(StringUtils.isNotBlank(useNameWithoutExt) && StringUtils.isNotBlank(oldNameExt)){
				newOldName = new StringBuilder(useNameWithoutExt).append(".").append(oldNameExt).toString();
			}
			if(StringUtils.isNotBlank(newOldName)){
				System.out.println("newOldName : "+newOldName);
			}else{
				System.out.println("newOldName : "+newOldName);
			}
		}	
	}
}
