package com.example.openldapbindissue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ldap.support.LdapNameBuilder;

import javax.naming.Name;

@SpringBootTest
class OpenldapbindissueApplicationTests {

	@Autowired
	OdmPersonRepo odmPersonRepo;

	@Test
	void findAllPersons(){
		odmPersonRepo.findAll().forEach(System.out::println);
		//Person(dn=cn=user1,ou=someOu, fullName=user1, company=someOu, someUnmappedField=null)
	}

	@Test
	void createPerson(){
		Person person = new Person();
		person.setFullName("testUsser");
		//DnAttribute for field company on class com.example.openldapbindissue.Person is null; cannot build DN
		//java.lang.IllegalStateException: DnAttribute for field company on class com.example.openldapbindissue.Person is null; cannot build DN

		Name dn = LdapNameBuilder.newInstance("cn=testUsser,ou=someOu,dc=example,dc=org").build();
		person.setDn(dn);
		//[LDAP: error code 65 - object class 'person' requires attribute 'sn']; nested exception is javax.naming.directory.SchemaViolationException: [LDAP: error code 65 - object class 'person' requires attribute 'sn']; remaining name 'cn=testUsser,ou=someOu,dc=example,dc=org'
		//org.springframework.ldap.SchemaViolationException: [LDAP: error code 65 - object class 'person' requires attribute 'sn']; nested exception is javax.naming.directory.SchemaViolationException: [LDAP: error code 65 - object class 'person' requires attribute 'sn']; remaining name 'cn=testUsser,ou=someOu,dc=example,dc=org'
		//	at app//org.springframework.ldap.support.LdapUtils.convertLdapException(LdapUtils.java:219)
		//	at app//org.springframework.ldap.core.LdapTemplate.executeWithContext(LdapTemplate.java:824)
		//	at app//org.springframework.ldap.core.LdapTemplate.executeReadWrite(LdapTemplate.java:816)
		//	at app//org.springframework.ldap.core.LdapTemplate.bind(LdapTemplate.java:994)
		//	at app//org.springframework.ldap.core.LdapTemplate.bind(LdapTemplate.java:1335)
		//	at app//org.springframework.ldap.core.LdapTemplate.create(LdapTemplate.java:1738)
		//	at app//com.example.openldapbindissue.OdmPersonRepo.create(OdmPersonRepo.java:17)

		person.setSn("testUsser");
		//[LDAP: error code 32 - No Such Object]; nested exception is javax.naming.NameNotFoundException: [LDAP: error code 32 - No Such Object]; remaining name 'cn=testUsser,ou=someOu,dc=example,dc=org'
		//org.springframework.ldap.NameNotFoundException: [LDAP: error code 32 - No Such Object]; nested exception is javax.naming.NameNotFoundException: [LDAP: error code 32 - No Such Object]; remaining name 'cn=testUsser,ou=someOu,dc=example,dc=org'
		//	at app//org.springframework.ldap.support.LdapUtils.convertLdapException(LdapUtils.java:183)
		//	at app//org.springframework.ldap.core.LdapTemplate.executeWithContext(LdapTemplate.java:824)
		//	at app//org.springframework.ldap.core.LdapTemplate.executeReadWrite(LdapTemplate.java:816)
		//	at app//org.springframework.ldap.core.LdapTemplate.bind(LdapTemplate.java:994)
		//	at app//org.springframework.ldap.core.LdapTemplate.bind(LdapTemplate.java:1335)
		//	at app//org.springframework.ldap.core.LdapTemplate.create(LdapTemplate.java:1738)
		//	at app//com.example.openldapbindissue.OdmPersonRepo.create(OdmPersonRepo.java:17)

		odmPersonRepo.create(person);
	}



	@Autowired
	PersonRepositoryRaw personRepositoryRaw;
	@Test
	void createPersonRaw(){
		personRepositoryRaw.createPerson();
		//cn=user1: javax.naming.directory.DirContext
		//cn=testuser2: javax.naming.directory.DirContext
	}




}
