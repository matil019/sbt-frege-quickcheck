package xyz.denshi_no_yamaoku.quickcheck;

/**
 * A marker interface.
 *
 * Modules implementing this interface will be discovered by QuickCheckFramework.
 *
 * Have your Frege modules implement this interface by including the following lines:
 * <code>
 * data Properties = pure native xyz.denshi_no_yamaoku.quickcheck.Properties
 * native module interface Properties where {
 *   // more java code, if any
 * }
 * </code>
 * See the issue #20 in Frege/frege for more details of this syntax.
 * https://github.com/Frege/frege/issues/20
 */
public interface Properties {}
