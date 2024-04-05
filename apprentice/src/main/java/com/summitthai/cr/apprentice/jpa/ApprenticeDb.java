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
/**
 *
 */
package com.summitthai.cr.apprentice.jpa;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * <p>
 * This is a {@code CDI: Qualifier} which identifies the {@code sandbox}
 * connection.
 * </p>
 *
 * @author charlee.ch
 * @version 0.0.1
 * @since 0.0.1
 */
@Inherited
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({
    ElementType.METHOD,
    ElementType.FIELD,
    ElementType.PARAMETER,
    ElementType.TYPE
})
public @interface ApprenticeDb {

}
