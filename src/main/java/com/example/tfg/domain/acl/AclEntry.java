package com.example.tfg.domain.acl;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;


/**
 * AclEntry generated by hbm2java
 */
@Entity
@Table(name = "acl_entry", uniqueConstraints = @UniqueConstraint(columnNames = {
                "acl_object_identity", "ace_order" }))
public class AclEntry implements java.io.Serializable {

        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Long id;
        private AclSid aclSid;
        private AclObjectIdentity aclObjectIdentity;
        private int aceOrder;
        private int mask;
        private boolean granting;
        private boolean auditSuccess;
        private boolean auditFailure;

        public AclEntry() {
        }

        public AclEntry(AclSid aclSid, AclObjectIdentity aclObjectIdentity,
                        int aceOrder, int mask, boolean granting, boolean auditSuccess,
                        boolean auditFailure) {
                this.aclSid = aclSid;
                this.aclObjectIdentity = aclObjectIdentity;
                this.aceOrder = aceOrder;
                this.mask = mask;
                this.granting = granting;
                this.auditSuccess = auditSuccess;
                this.auditFailure = auditFailure;
        }

        @Id
        @GeneratedValue(strategy = IDENTITY)
       // @Id
    	//@GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "ID", unique = true, nullable = false)
        public Long getId() {
                return this.id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "SID", nullable = false)
        public AclSid getAclSid() {
                return this.aclSid;
        }

        public void setAclSid(AclSid aclSid) {
                this.aclSid = aclSid;
        }

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "ACL_OBJECT_IDENTITY", nullable = false)
        public AclObjectIdentity getAclObjectIdentity() {
                return this.aclObjectIdentity;
        }

        public void setAclObjectIdentity(AclObjectIdentity aclObjectIdentity) {
                this.aclObjectIdentity = aclObjectIdentity;
        }

        @Column(name = "ACE_ORDER", nullable = false)
        public int getAceOrder() {
                return this.aceOrder;
        }

        public void setAceOrder(int aceOrder) {
                this.aceOrder = aceOrder;
        }

        @Column(name = "MASK", nullable = false)
        public int getMask() {
                return this.mask;
        }

        public void setMask(int mask) {
                this.mask = mask;
        }

        @Column(name = "GRANTING", nullable = false)
        public boolean isGranting() {
                return this.granting;
        }

        public void setGranting(boolean granting) {
                this.granting = granting;
        }

        @Column(name = "AUDIT_SUCCESS", nullable = false)
        public boolean isAuditSuccess() {
                return this.auditSuccess;
        }

        public void setAuditSuccess(boolean auditSuccess) {
                this.auditSuccess = auditSuccess;
        }

        @Column(name = "AUDIT_FAILURE", nullable = false)
        public boolean isAuditFailure() {
                return this.auditFailure;
        }

        public void setAuditFailure(boolean auditFailure) {
                this.auditFailure = auditFailure;
        }

}