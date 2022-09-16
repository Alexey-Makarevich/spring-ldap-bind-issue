# spring-ldap-bind-issue

This project uses Person and OdmPersonRepo classes from https://docs.spring.io/spring-ldap/docs/2.4.0/reference/#odm .
It shows that bind can not create person (item) in Ldap.

- Start OpenLDAP 
- - use resources/docker-compose.yml - it is official docker-compose from https://github.com/osixia/docker-openldap/blob/master/example/docker-compose.yml
- import example ou and cn - resources/example.ldif
- run com.example.openldapbindissue.OpenldapbindissueApplicationTests.findAllPersons and check that Person is available
- run com.example.openldapbindissue.OpenldapbindissueApplicationTests.createPerson - you will get "LDAP: error code 32 - No Such Object"

But if we use javax.naming.directory.DirContext.createSubcontext
- run com.example.openldapbindissue.OpenldapbindissueApplicationTests.createPersonRaw - it successfully creates person
