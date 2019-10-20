package ru.itmo.wp.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uris = request.getRequestURI();
        String path = getServletContext().getRealPath(".") + "/../../src/main/webapp/static/";

        boolean contentTypeDetermined = false;
        for (String uri : uris.split("\\+")) {
            if (uri.charAt(0) != '/') uri = '/' + uri;

            File file = new File(path, uri);
            if (!file.isFile())
                file = new File(getServletContext().getRealPath("/static" + uri));

            if (file.isFile()) {
                if (!contentTypeDetermined) {
                    response.setContentType(getContentTypeFromName(uri));
                    contentTypeDetermined = true;
                }

                OutputStream outputStream = response.getOutputStream();
                Files.copy(file.toPath(), outputStream);
                outputStream.flush();
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    private String getContentTypeFromName(String name) {
        name = name.toLowerCase();

        if (name.endsWith(".png")) {
            return "image/png";
        }

        if (name.endsWith(".jpg")) {
            return "image/jpeg";
        }

        if (name.endsWith(".html")) {
            return "text/html";
        }

        if (name.endsWith(".css")) {
            return "text/css";
        }

        if (name.endsWith(".js")) {
            return "application/javascript";
        }

        throw new IllegalArgumentException("Can't find content type for '" + name + "'.");
    }
}

