/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.26
 * Generated at: 2014-07-21 14:08:31 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import model.*;

public final class offertePage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("\t\t\n");
      out.write("\t\t<title>Agenzia Immobiliare</title>\n");
      out.write("\t\t<link type=\"text/css\" rel=\"stylesheet\" href=\"css/style.css\">\n");
      out.write("\t\t<link rel=\"shortcut icon\" href=\"images/favicon.ico\" /> \n");
      out.write("\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\t\n");
      out.write("   \t<div id=\"puntina1\"> <img src= \"images/tack.png\" width=\"100\" height=\"100\"></div>\n");
      out.write("\t<div id=\"puntina2\"> <img src= \"images/tack.png\" width=\"100\" height=\"100\"></div>\n");
      out.write("\t<div id=\"puntina3\"> <img src= \"images/tack.png\" width=\"100\" height=\"100\"></div>\n");
      out.write("\n");
      out.write("\t<div class=\"header\">\n");
      out.write("\t\n");
      out.write("\t\t<div id=\"menu\"> \n");
      out.write("\n");
      out.write("\t\t<div class=\"logo\"><h1><a href=\"./main\">Agenzia Immobiliare</a> </h1> </div>\n");
      out.write("\n");
      out.write("\t\t\t<ul>\n");
      out.write("\t\t\t   <li><a href=\"./main\">Home</a></li>\n");
      out.write("\t\t\t   <li><a href=\"./main#chisiamo\">Chi siamo</a></li>\n");
      out.write("\t\t\t   <li><a href=\"./main#invendita\">Cerco casa</a></li>\n");
      out.write("\t\t\t   <li class=\"no_bg ped\"><a href=\"./main#contactus\">Dove siamo</a></li>\n");
      out.write("\t\t\t</ul>\n");
      out.write("\t\t</div> <!-- end menu-->\t\n");
      out.write("\t\t\n");
      out.write("\t</div> <!-- end header-->\n");
      out.write("\n");
      out.write("\t<div class=\"content\">\n");
      out.write("\t\t<div class=\"container\">\n");
      out.write("\n");
      out.write("\n");
      out.write("     ");
 
     
        Vector offerte = (Vector)request.getAttribute("offerteImm"); 
        
    
      out.write("\n");
      out.write("            \n");
      out.write("   <h3>\t\n");
      out.write("\t\t<div class=\"titolo\"><h2>Pagina relativa alle offerte sull'immobile.</h2></div>\n");
      out.write("\t\t\t<ul> Elenco offerte\n");
      out.write("                ");
 for (int i = 0; i < offerte.size(); i++) {
                    agenziaImmoBean bean = (agenziaImmoBean) offerte.get(i);
                 
      out.write("\n");
      out.write("\t\t\t       <li> Giorno offerta: ");
      out.print( bean.getGiornoContattoPA());
      out.write(" </li>\n");
      out.write("                   <li> Cognome acquirente: ");
      out.print( bean.getCognomePA() );
      out.write(" </li> \n");
      out.write("                   <li> Nome acquirente: ");
      out.print( bean.getNomePA() );
      out.write(" </li>\n");
      out.write("                   <li> Numero di telefono acquirente: ");
      out.print( bean.getTelefonoPA() );
      out.write(" </li>\n");
      out.write("                   <li> Prezzo offerto: ");
      out.print( bean.getPrezzoOfferto() );
      out.write(" </li>\n");
      out.write("        \n");
      out.write("              ");
 } 
      out.write("\n");
      out.write("\t\t\t  \n");
      out.write("\t\t\t</ul>\n");
      out.write("\t\t   </h3>\n");
      out.write("\n");
      out.write("    \n");
      out.write("\t\t</div> <!-- container end -->\n");
      out.write("\t</div>\t<!-- content-end-->\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("\n");
      out.write("<footer>\n");
      out.write("\t<h3> Progetto prodotto da Pietro Musoni e Carlo Tacchella. Corso di Base di dati. Anno 2013/2014.</h3>\n");
      out.write("</footer>\n");
      out.write("</html>\n");
      out.write("\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
