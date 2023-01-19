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
		person.setCompany("someOu");
		person.setSn("testUsser");
		odmPersonRepo.create(person);
	}

	@Test
	void createPersonSpecifyingDn(){
		Person person = new Person();
		person.setFullName("testUsser2");
		person.setSn("testUsser2");
		Name dn = LdapNameBuilder.newInstance("cn=testUsser2,ou=someOu").build();
		person.setDn(dn);
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
