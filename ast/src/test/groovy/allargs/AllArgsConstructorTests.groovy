package allargs

class AllArgsConstructorTests extends GroovyTestCase {
    void 'testConstructorGeneration'() {
        assertScript '''
            import com.nothing.allargsconstructor.AllArgsConstructor

            @AllArgsConstructor
            class SimpleClass {
                public String firstField
                public String secondField
            }

            def x = new SimpleClass("aa", "bb")
        '''
    }
}
