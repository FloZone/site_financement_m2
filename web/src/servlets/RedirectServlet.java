package servlets;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public abstract class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public void redirectTo(Class<? extends HttpServlet> servletClass, HttpServletResponse response) throws IOException {
        if (servletClass.isAnnotationPresent(WebServlet.class)) {     	
            String urlPattern = getPath(servletClass);       
            redirectToPath(urlPattern, response);
        }
        else {
            throw new IllegalArgumentException("WebServlet annotation not found on servletClass");
        }
    }
    
    public void redirectTo(Class<? extends HttpServlet> servletClass, HttpServletResponse response, Map<String, String> parameters) throws IOException {
        if (servletClass.isAnnotationPresent(WebServlet.class)) {
            String urlPattern = getPath(servletClass);
            StringBuilder sb = new StringBuilder(urlPattern);
            sb.append("?");
            
            Iterator<Map.Entry<String, String>> it = parameters.entrySet().iterator();
        	
            while (it.hasNext()) {
            	Map.Entry<String, String> entry = it.next();
            	
            	sb.append(entry.getKey());
            	sb.append("=");
            	sb.append(entry.getValue());
            	
            	if (it.hasNext()) {
            		sb.append("&");
            	}
            }
            
            redirectToPath(sb.toString(), response);
        }
        else {
            throw new IllegalArgumentException("WebServlet annotation not found on servletClass");
        }
    }
    
	private void redirectToPath(String servletPath, HttpServletResponse response) throws IOException {
        String path = getServletContext().getContextPath().concat(servletPath);
        response.sendRedirect(path);
    }
	
    private String getPath(Class<? extends HttpServlet> servletClass) {
    	WebServlet annotation = (WebServlet) servletClass.getAnnotation(WebServlet.class);
    	
        String urlPattern = null;
        String[] values = annotation.value();
        String[] urlPatterns = annotation.urlPatterns();
        
        if (urlPatterns.length == 0) {
        	urlPattern = values[0];
        }
        else  {
        	urlPattern = urlPatterns[0];
        }
        
        return urlPattern;
    }
}
