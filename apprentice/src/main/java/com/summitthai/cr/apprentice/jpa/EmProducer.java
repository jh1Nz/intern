/*
 * Copyright (c) 2020-2021 By Charlee Chitsuk
 *
 * All rights reserved. No part of this program and the accompanying
 * materials may be reproduced, distributed, or transmitted in
 * any form or by any means, including photocopying, recording, or
 * other electronic or mechanical methods, without the prior written
 * permission of the publisher, except in the case of brief quotations
 * embodied in critical reviews and certain other noncommercial uses
 * permitted by copyright law. For permission requests, write to the
 * publisher, addressed "Attention: Permissions Coordinator," at the
 * address below.
 *
 * Charlee Chitsuk
 *
 * Summit Computer Co.,Ltd.
 * 109 C.C.T. Building, 12th Floor,
 * Surawong Road, Suriyawong,
 * Bangrak, Bangkok,
 * Thailand. 10500.
 *
 * Tel. 66-2-237-6922 to 3, 66-2-238-0895 to 9
 * Fax: 66-2-236-7392
 * email: charlee@summitthai.com
 * website: http://www.summitthai.com
 */
package com.summitthai.cr.apprentice.jpa;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.summitthai.sdd.dao.jpa.EmProducible;

/**
 * <p>
 * This is a concrete implementing class which provides the feature described at
 * {@link EmProducible}.
 * </p>
 *
 * @author charlee.ch
 * @version 0.0.1
 * @since 0.0.1
 * @see EmProducible
 */
@ApplicationScoped
public class EmProducer implements EmProducible,Serializable {

    /**
     * This is a default serial version {@code UID} as {@value}.
     *
     * @since 0.0.1
     */
    private static final long serialVersionUID = 1L;

    /**
     * This is a variable which represents the {@link EntityManagerFactory}.
     *
     * @since 0.0.1
     */
    @Inject
    @ApprenticeDb
    private EntityManagerFactory emf;

    @Override
    @ApprenticeDb
    @RequestScoped
    @Produces
    public EntityManager produce() {
        return this.emf.createEntityManager();
    }

    @Override
    public void dispose(@Disposes
    					@ApprenticeDb
                        final EntityManager disposingEm) {
        disposingEm.close();
    }

}
