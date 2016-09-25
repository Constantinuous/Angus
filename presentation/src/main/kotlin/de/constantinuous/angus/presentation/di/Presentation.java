package de.constantinuous.structipus.presentation.di;

import de.constantinuous.structipus.shared.di.api.Binder;
import de.constantinuous.structipus.shared.di.impl.PicoBinder;

/**
 * Created by RichardG on 08.02.2016.
 */
public class Presentation extends PicoBinder implements Binder {

    public final static Binder DI = new Presentation();

}
