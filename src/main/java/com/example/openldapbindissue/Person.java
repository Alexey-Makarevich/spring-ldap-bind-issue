package com.example.openldapbindissue;

import lombok.Data;
import org.springframework.ldap.odm.annotations.*;

import javax.naming.Name;

@Data
@Entry(objectClasses = { "person", "top" }, base="ou=someOu")
public class Person {
    @Id
    private Name dn;

    @Attribute(name="cn")
    @DnAttribute(value="cn", index=1)
    private String fullName;

    @Attribute(name="sn")
    private String sn;

    @DnAttribute(value="ou", index=0)
    @Transient
    private String company;

    @Transient
    private String someUnmappedField;
    // ...more attributes below


    //# Entry 1: ou=someOu,dc=example,dc=org
    //dn: ou=someOu,dc=example,dc=org
    //objectclass: organizationalUnit
    //objectclass: top
    //ou: someOu

    //# Entry 1: cn=user1,ou=someOu,dc=example,dc=org
    //dn: cn=user1,ou=someOu,dc=example,dc=org
    //cn: user1
    //objectclass: person
    //objectclass: top
    //sn: user1








}
