package com.nagappans.rest;

import com.nagappans.model.FileUploadForm;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

@Path("upload-file")
public class UploadFileService {

    @POST
    @Path("/")
    @Consumes("multipart/form-data")
    public Response uploadFile(@MultipartForm MultipartFormDataInput form) {

        Map<String, List<InputPart>> formDataMap = form.getFormDataMap();
        String fileName = null;


        try
        {
            fileName = formDataMap.get("fileName").get(0).getBodyAsString();
            if (fileName==null) {
                fileName = "Unknown";
            }
            String completeFilePath = "/tmp/" + fileName;
            //Save the file
            File file = new File(completeFilePath);

            if (!file.exists())
            {
                file.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(file);
            List<InputPart> inputParts = formDataMap.get("selectedFile");
            for(InputPart inputPart: inputParts) {
                InputStream inputStream = inputPart.getBody(InputStream.class, null);
                byte[] filedatabytes = IOUtils.toByteArray(inputStream);
                fos.write(filedatabytes);
            }

            fos.flush();
            fos.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        //Build a response to return
        return Response.status(200)
                .entity("uploadFile is called, Uploaded file name : " + fileName).build();
    }
}
