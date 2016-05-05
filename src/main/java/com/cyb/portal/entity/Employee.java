package com.cyb.portal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cyb.portal.entity.base.BaseEntity;

import java.io.Serializable;

@Entity
@Table(name = "Employee")
public class Employee extends BaseEntity {
	
	private static final long serialVersionUID = -7988799579036225137L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
    @Column
    private String name;
    
    @Column
    private int age;
    
    @Column
    private float salary;

    public Employee() {
    	super();
    }
    
    
    public Employee(long id, String name, int age, float salary) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
	}


	public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public float getSalary() {
        return salary;
    }
    public void setSalary(float salary) {
        this.salary = salary;
    }
    
}