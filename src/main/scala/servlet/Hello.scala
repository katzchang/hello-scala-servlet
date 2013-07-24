package servlet

import javax.servlet.http.{Cookie, HttpServletResponse, HttpServletRequest, HttpServlet}
import javax.servlet.annotation.WebServlet
import scala.collection.JavaConversions._

@WebServlet(Array("/hello"))
class Hello extends HttpServlet {
  override def doGet(request: HttpServletRequest, response: HttpServletResponse) {
    val parameters = request.getParameterMap
    val cookies = parseCookies(request)

    parameters.foreach((entry) => createOrResetCookie(response, entry._1, entry._2.mkString(",")))

    response.getWriter.println("parameters:" + parameters.toString)
    response.getWriter.println("cookies:" + cookies.toString)
    response.flushBuffer()
  }

  private def parseCookies(request: HttpServletRequest): Map[String, String] =
    Option(request.getCookies).getOrElse(Array.empty).toList.map((cookie) => (cookie.getName, cookie.getValue)).toMap

  private def createOrResetCookie(response: HttpServletResponse, name: String, value: String) = {
    val uuidCookie: Cookie = new Cookie(name, value)
    uuidCookie.setPath("/")
    uuidCookie.setMaxAge(7776000) // 3 months
    response.addCookie(uuidCookie)
  }
}
