package com.example.openldapbindissue;

import org.springframework.stereotype.Service;

import javax.naming.Context;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Hashtable;

@Service
public class PersonRepositoryRaw {

    public void createPerson() {


        // Set up the environment for creating the initial context
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://openldap:389/ou=someOu,dc=example,dc=org");

        // Authenticate as admin and password "password"
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "cn=admin,dc=example,dc=org");
        env.put(Context.SECURITY_CREDENTIALS, "admin");

        try {
            // Create the initial context
            DirContext ctx = new InitialDirContext(env);

            // Create attributes to be associated with the new context
            Attributes attrs = new BasicAttributes(true); // case-ignore
            Attribute objclass = new BasicAttribute("objectclass");
            objclass.add("top");
            objclass.add("person");
            attrs.put(objclass);
            attrs.put("sn","testuser2");

            // Create the context
            Context result = ctx.createSubcontext("cn=testuser2", attrs);

            // Check that it was created by listing its parent
            NamingEnumeration list = ctx.list("");

            // Go through each item in list
            while (list.hasMore()) {
                NameClassPair nc = (NameClassPair)list.next();
                System.out.println(nc);
            }

            // Close the contexts when we're done
            result.close();
            ctx.close();
        } catch (NamingException e) {
            System.out.println("Create failed: " + e);
        }


    }


}
