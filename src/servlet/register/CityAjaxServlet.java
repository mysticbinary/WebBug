package servlet.register;

import entity.City;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import com.alibaba.fastjson.JSON;

@WebServlet(name = "CityAjaxServlet")
public class CityAjaxServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");

        ArrayList mylist = new ArrayList();

        // 先用写死的数据，后期改成 读取本地硬盘序列化的数据，然后find 反序列化漏洞 的特征
        mylist.add(buildCity("北京", "01"));
        mylist.add(buildCity("天津", "02"));

        String userJson = JSON.toJSONString(mylist);
        OutputStream out = response.getOutputStream();
        out.write(userJson.getBytes("UTF-8"));
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private City buildCity(String cityname, String value) {
        City city = new City();
        city.setName(cityname);
        city.setValue(value);
        return city;
    }
}
