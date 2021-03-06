package com.lntormin.javaee.web.controllers.impl;

import com.lntormin.javaee.ejb.remote.UserBeanRemote;
import com.lntormin.javaee.ejb.entities.User;
import com.lntormin.javaee.web.controllers.AbstractController;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author lntormin
 */
public class SearchController extends AbstractController {
    @Override
    public void execute() {
        try {
            Logger.getLogger(SearchController.class.getName()).log(Level.INFO, null, "Search");

            String name = this.getRequest().getParameter("name");
            Context context = new InitialContext();
            UserBeanRemote userBean = (UserBeanRemote) context.lookup("java:global/EnterpriseApp/EJBModule-ejb/UserBean");

            List<User> users = (List) userBean.searchUserByName(name);
            this.setReturnPage("/listUsers.jsp");
            this.getRequest().setAttribute("users", users);
        } catch (Exception ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
