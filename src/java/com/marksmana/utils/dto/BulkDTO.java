/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marksmana.utils.dto;

import com.marksmana.entities.Bulk;
import com.marksmana.entities.Clazz;
import com.marksmana.entities.Subject;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Giang
 */
public class BulkDTO implements Serializable{
    private int id;
    private String name;
    private String info;
    private List<Subject> subjectList;
    private List<Clazz> clazzList;

    public BulkDTO(String name, String info, List<Subject> subjectList, List<Clazz> clazzList) {
        this.name = name;
        this.info = info;
        this.subjectList = subjectList;
        this.clazzList = clazzList;
    }

    public BulkDTO(Integer id, String name, String info, List<Subject> subjectList, List<Clazz> clazzList) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.subjectList = subjectList;
        this.clazzList = clazzList;
    }

    public BulkDTO() {
    }

    public BulkDTO(Bulk b) {
        id = b.getId();
        name = b.getName();
        info = b.getInfo();
    }
}
