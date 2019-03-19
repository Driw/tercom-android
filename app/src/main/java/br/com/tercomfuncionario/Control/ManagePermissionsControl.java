package br.com.tercomfuncionario.Control;

import android.app.Activity;
import android.util.Pair;

import java.util.TreeMap;

import br.com.tercomfuncionario.Entity.ApiResponse;
import br.com.tercomfuncionario.Entity.PermissionList;
import br.com.tercomfuncionario.Enum.EnumMethod;
import br.com.tercomfuncionario.Enum.EnumREST;
import br.com.tercomfuncionario.Util.CustomPair;

public class ManagePermissionsControl extends GenericControl {
    private Activity activity;

    public ManagePermissionsControl(Activity activity){
        this.activity = activity;
    }

    public ApiResponse getAll(int assignmentLevel){
        try{
            String link = getLink(getBase(EnumREST.SITE, EnumREST.MANAGEPERMISSIONS, EnumREST.GETALL, EnumREST.TERCOM), String.valueOf(assignmentLevel));
            Pair<String, String> completePost = new Pair<>(link, getPostValues(new TreeMap<String, String>(), false));
            CustomPair<String> jsonResult =  callJson(EnumMethod.POST,activity,completePost);
            ApiResponse<PermissionList> permissionListApiResponse= new ApiResponse<>(PermissionList.class);
            if(jsonResult.first){
                permissionListApiResponse = populateApiResponse(permissionListApiResponse,jsonResult.second);
            }
            return permissionListApiResponse;
        }
        catch(Exception e){
            e.printStackTrace();
            return getErrorResponse();
        }
    }
}

