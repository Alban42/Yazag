/**
 *
 */
package com.alban42.yazag.utils.objects;

import com.alban42.yazag.common.utils.objects.world.WorldUpdateObject;

/**
 * @author Alban
 */
public abstract class AbstractGameObjectFactory<T extends AbstractGameObject> {

    public abstract T createNewObject(WorldUpdateObject worldUpdateObject);

}
