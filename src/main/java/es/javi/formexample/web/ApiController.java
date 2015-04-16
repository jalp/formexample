package es.javi.formexample.web;

import es.javi.formexample.domain.Container;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ApiController {
    private static final Logger logger = Logger.getLogger(ApiController.class);

    @Autowired
    private Environment env;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        logger.info("Redirecting to index...");
        return "index.html";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile[] file,
                         @RequestParam("subject") String subject,
                         @RequestParam("body") String body,
                         @RequestParam("price") int price,
                         @RequestParam("description") String description) {

        Container container = new Container();
        container.setBody(body);
        container.setSubject(subject);
        container.setPrice(price);
        container.setDescription(description);

        String directory = env.getProperty("application.path.uploadedFiles");

        List<String> names = new ArrayList<>();

        if (file != null && file.length > 0) {
            for (MultipartFile f : file) {
                try {
                    String fileName = f.getOriginalFilename();
                    if (!fileName.isEmpty()) {
                        names.add(fileName);
                        byte[] bytes = f.getBytes();
                        BufferedOutputStream stream =
                                new BufferedOutputStream(new FileOutputStream(new File(Paths.get(directory, fileName).toString())));
                        stream.write(bytes);
                        stream.close();
                    }
                } catch (IOException e) {
                    return "You failed to upload: '" + f.getOriginalFilename() + "' => " + e.getMessage();
                }
            }
            container.setFile(names);
        } else {
            logger.error("No file data in POST");
            return "Empty file upload";
        }
        logger.info(container);
        return "OK";
    }
}
