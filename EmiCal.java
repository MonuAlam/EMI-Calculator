import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;


public class EmiCal extends HttpServlet{

public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{

res.setContentType("text/html");
PrintWriter out=res.getWriter();

Double amount=Double.parseDouble(req.getParameter("amount"));
Double interest=Double.parseDouble(req.getParameter("interest"));
Double months=Double.parseDouble(req.getParameter("months"));


req.setAttribute("amount",amount);
req.setAttribute("interest",interest);
req.setAttribute("months",months);

RequestDispatcher rd=req.getRequestDispatcher("/emi1");
rd.forward(req,res);


}

}