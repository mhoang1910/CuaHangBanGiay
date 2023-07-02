/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories.impl;

import DomainModels.NhaCungCap;
import Repositories.IRepositoryNhaCungCap;
import Utilies.HibernateUtil;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ADMIN
 */
public class RepositoryNhaCungCap implements IRepositoryNhaCungCap {

    private Session ss = HibernateUtil.getSession();

    @Override
    public List<NhaCungCap> getNCC() {
        Query query = ss.createQuery("From NhaCungCap", NhaCungCap.class);
        List<NhaCungCap> list = query.getResultList();

        return list;
    }

    @Override
    public List<NhaCungCap> checkMa(String ma) {
        ss = HibernateUtil.getSession();
        Query query = ss.createQuery("From NhaCungCap where MA = :MA", NhaCungCap.class);
        query.setParameter("MA", ma);
        return query.getResultList();
    }

    @Override
    public Boolean themNCC(NhaCungCap ncc) {
        try {
            Transaction tran = ss.beginTransaction();
            ss.save(ncc);
            tran.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean updateNCC(NhaCungCap ncc, String ma) {
        try {
            Transaction tran = ss.beginTransaction();
            Query query = ss.createQuery("Update NhaCungCap set Ten = :Ten, DiaChi = :DiaChi, Sdt=:Sdt, TrangThai=:TrangThai where Ma=:Ma ");
            query.setParameter("Ma", ma);
            query.setParameter("Ten", ncc.getTen());
            query.setParameter("DiaChi", ncc.getDiaChi());
            query.setParameter("Sdt", ncc.getSdt());
            query.setParameter("TrangThai", ncc.getTrangThai());
            query.executeUpdate();
            tran.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
