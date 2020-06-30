package singletonpattern;

/**
 * 
 * The Singleton Pattern ensures you have at most one instance of a class in
 * your application. Java’s implementation of the Singleton Pattern makes use of
 * a private constructor, a static method combined with a static variable.
 * 
 * lazy instantiation, advantage over global variables.
 * 
 * If you make all methods, variable static, yes, if your class is
 * self-contained and doesn’t depend on complex initialization. However, because
 * of the way static initializations are handled in Java, this can get very
 * messy, especially if multiple classes are involved. Often this scenario can
 * result in subtle, hard-to-find bugs involving order of initialization. Unless
 * there is a compelling need to implement your “singleton” this way, it is far
 * better to stay in the object world.
 * 
 * If you have two or more class loaders, you can load the same class multiple
 * times (once in each classloader). Now, if that class happens to be a
 * Singleton, then since we have more than one version of the class, we also
 * have more than one instance of the Singleton
 * 
 * @author rakshim1
 *
 */
public class Main {

	public static void main(String[] args) {

	}

}
