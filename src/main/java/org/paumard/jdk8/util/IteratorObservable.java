/*
 * Copyright (C) 2015 José Paumard
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package org.paumard.jdk8.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;

import rx.Observable;

public class IteratorObservable<E> {

	
	interface Wrapper<E> {
		E get() ;
		
		default Wrapper<E> set(E e) {
			return () -> e ;
		}
	}

	public static <E> Iterator<E> of(Observable<E> observable) {
		Objects.requireNonNull(observable) ;
		
		class Adapter implements Iterator<E>, Consumer<E> {
			
			Wrapper<Boolean> valueReady ;
            Wrapper<E> nextElement;
            
            public Adapter() {
            	observable.subscribe(
            		e -> nextElement.set(e), 
            		t -> valueReady.set(false), 
            		() -> valueReady.set(false)
            	) ;
            }

            @Override
            public void accept(E e) {
                valueReady.set(true);
                nextElement = () -> e;
            }

            @Override
            public boolean hasNext() {
                return valueReady.get();
            }

            @Override
            public E next() {
                if (!valueReady.get() && !hasNext())
                    throw new NoSuchElementException();
                else {
                    valueReady.set(false);
                    return nextElement.get() ;
                }
            }
        }

        return new Adapter();
	}
}
