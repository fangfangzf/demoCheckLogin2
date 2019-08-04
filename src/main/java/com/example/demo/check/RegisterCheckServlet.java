package com.example.demo.check;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class RegisterCheckServlet extends HttpServlet {

    public static final String DBDRIVER="com.mysql.jdbc.Driver";
    public static final String DBURL="jdbc:mysql://localhost:8080/register";
    public static final String DBUSER="root";
    public static final String DBPASS="12345678";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{

        request.setCharacterEncoding("gbk");
        response.setContentType("text/html");
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        PrintWriter out=response.getWriter();
        String username=request.getParameter("username");//接受验证的用户名
        out.println(username);

        try{
            Class.forName(DBDRIVER);
            conn=DriverManager.getConnection(DBURL,DBUSER,DBPASS);
            String sql=" select * from name where username=?";
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,username);
            rs=pstmt.executeQuery();
            if(rs.next()){

                if(rs.getString("username")==username){
                    out.println("true");

                }else{
                    out.println("false");
                }
            }
            out.close();

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
