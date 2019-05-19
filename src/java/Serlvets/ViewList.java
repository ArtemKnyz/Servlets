package Serlvets;

import Class.Attribute;
import Class.Parameter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        if (request.getParameter("new") != null) {
            System.out.println("Введите новый параметр");
            request.getRequestDispatcher("index.html").forward(request, response);

        }

        String SourceStr = null;

        String data = request.getParameter("userName");

        int min = Integer.MIN_VALUE;
        try {
            min = Integer.parseInt(request.getParameter("min"));
        } catch (NumberFormatException e) {
            //something to do
        }

        Attribute attr = (Attribute) getServletContext().getAttribute("attr");

        int max = Integer.MAX_VALUE;
        try {
            max = Integer.parseInt(request.getParameter("max"));
        } catch (NumberFormatException e) {
            //something to do
        }

        if (attr != null) {
            ArrayList<Parameter> users = (ArrayList) attr.getUsers();
            StringBuilder str = new StringBuilder();
            str.append(users.containsAll(users));

            for (Parameter prm : users) {
                if (prm.getName().contains(data)) {
                    if (max > prm.getVal() && prm.getVal() >= min) {
                        str.append(prm.getName()).append(" : ").append(prm.getVal()).append("<br>");
                    }
                }
            }
            if (str.length() != 0) {
                SourceStr = str.toString();
            } else {
                SourceStr = "not found1";
            }
        } else {
            SourceStr = "not found2";
        }

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>" + SourceStr + "</h3>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
    }

}
