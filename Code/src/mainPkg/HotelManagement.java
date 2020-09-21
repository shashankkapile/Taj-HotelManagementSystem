/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPkg;
import control.Login_controller;
import java.io.IOException;

public class HotelManagement 
{
    public static void main(String[] args) throws IOException 
    {
        Login_controller controller=new Login_controller();
        controller.startApplication();
    }
    
}
