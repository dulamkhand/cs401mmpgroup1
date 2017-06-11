package model.account;

import model.account.*;
import util.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author bek
 */
public class AccountEmployeeVendorDAO {
    
    public static EmployeeAccount findEmployee(String login) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM account WHERE login_name = '"+login +"'";
        try {
            ResultSet rs = DBUtil.dbExecuteQuery(selectStmt);
          
            EmployeeAccount o = null;
            
            if (rs.next()) {
                String type = rs.getString("TYPE");
                System.out.println("TYPE: " + type);
                
                if(type.equals(AccountTypeEnum.SUPER_USER.toString())){
                    o = EmployeeAccountFactory.createSuperUser(rs.getString("NUMBER"), rs.getString("vend_emp_id"), rs.getString("login_name"), rs.getString("password"));
                }else if(type.equals(AccountTypeEnum.EMPLOYEE.toString())){
                    System.out.println(rs.getString("number"));
                    String selectEmpStmt = "SELECT * FROM employee WHERE acc_number = '"+rs.getString("number")+ "'";
                    ResultSet rsEmp = DBUtil.dbExecuteQuery(selectEmpStmt);
                    if(rsEmp.next()){
                        o = EmployeeAccountFactory.createAccountEmployee(rs.getString("number"), rs.getString("vend_emp_id"), rs.getString("login_name"), rs.getString("password"), rsEmp.getString("first_name"), rsEmp.getString("last_name"));
                    }
                }
            }
            return o;
        } catch (SQLException e) {
            System.out.println("While searching a order with " + 
                    login + " id, an error occurred: " + e);
            throw e;
        }
    }
    
    public static ObservableList findEmployeeByNumberFuzzy(String number) throws SQLException, ClassNotFoundException {
        number = "%" + number + "%";
        String selectStmt = "SELECT employee.number as number, employee.acc_number as accNumber, account.login_name as login, employee.first_name as name, employee.last_name as surname" + 
" FROM employee " +
"INNER JOIN account ON employee.number=account.vend_emp_id and employee.number like '" + number + "'";
        try {
            ResultSet rs = DBUtil.dbExecuteQuery(selectStmt);
          
            ObservableList<EmployeeResult> list = FXCollections.observableArrayList();
            
            EmployeeResult empRes;
            int k = 1;
            while (rs.next()) {
                empRes = new EmployeeResult();
                empRes.setNumber(rs.getString("number"));
                empRes.setAccNumber(rs.getString("acc_number"));
                empRes.setLogin(rs.getString("login_name"));
                empRes.setName(rs.getString("first_name"));
                empRes.setSurename(rs.getString("last_name"));
                list.add(empRes);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("While searching a order with " + 
                    number + " number, an error occurred: " + e);
            throw e;
        }
    }
    
    public static VendorAccount findVendor(String login) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM account WHERE login_name = '"+login + "'";
        try {
            ResultSet rs = DBUtil.dbExecuteQuery(selectStmt);
          
            VendorAccount o = null;
            
            if (rs.next()) {
                String type = rs.getString("TYPE");
                
                if(type.equals(AccountTypeEnum.PERSON)){
                    String selectPersonStmt = "SELECT * FROM person WHERE vendor_number = '"+rs.getString("vend_emp_id") + "'";
                    ResultSet rsPerson = DBUtil.dbExecuteQuery(selectPersonStmt);
                    if(rsPerson.next()){
                        o = VendorAccountFactory.createVendorPerson(rs.getString("number"), rs.getString("login_name"), rs.getString("password"), rsPerson.getString("first_name"), rsPerson.getString("last_name"), rsPerson.getString("SSN"), rsPerson.getString("nationality"), rsPerson.getString("vendor_number"));
                    }
                }else if(type.equals(AccountTypeEnum.COMPANY)){
                    String selectCompanyStmt = "SELECT * FROM company WHERE vendor_number = '"+rs.getString("vend_emp_id") + "'";
                    ResultSet rsCompany = DBUtil.dbExecuteQuery(selectCompanyStmt);
                    if(rsCompany.next()){
                        o = VendorAccountFactory.createVendorCompany(rs.getString("number"), rs.getString("login_name"), rs.getString("password"), rsCompany.getString("comp_reg_number"), rsCompany.getString("name"), rsCompany.getString("company_rep"), rsCompany.getString("vendor_number"));
                    }
                }
            }
            return o;
        } catch (SQLException e) {
            System.out.println("While searching a order with " + 
                    login + " id, an error occurred: " + e);
            throw e;
        }
    }
    
    public static void insert(String accNumber, String login, String password, String empId, String firstName, String lastName) 
            throws SQLException, ClassNotFoundException {
        String updateStmt1 =
            "INSERT INTO `account` (`number`, `login_name`, `password`, `type`, `vend_emp_id`) "
            + " VALUES ('" + accNumber + "', '" + login + "', '" + password + "', '" + AccountTypeEnum.EMPLOYEE.toString() + "', '" + empId + "')";
        String updateStmt2 = " INSERT INTO `employee` (`number`, `first_name`, `last_name`, `acc_number`) "
                + " VALUES ('" + empId + "', '" + firstName + "', '" + lastName +  "', '" + accNumber + "')";
        try {
            DBUtil.dbExecuteUpdate(updateStmt1);
            DBUtil.dbExecuteUpdate(updateStmt2);
        } catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
   
//    public static void update (String id, String amount, Integer currency) 
//            throws SQLException, ClassNotFoundException {
//        String updateStmt =
//            "BEGIN\n" +
//                "   UPDATE Account\n" +
//                "      SET amount = '" + amount + "'\n" +
//                "      SET currency = '" + currency + "'\n" +
//                "    WHERE ID = " + id + ";\n" +
//                "   COMMIT;\n" +
//                "END;";
//        try {
//            DBUtil.dbExecuteUpdate(updateStmt);
//        } catch (SQLException e) {
//            System.out.print("Error occurred while UPDATE Operation: " + e);
//            throw e;
//        }
//    }

}