package hr.ericsson.etkc;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("hr.ericsson.etkc");

        noClasses()
            .that()
                .resideInAnyPackage("hr.ericsson.etkc.service..")
            .or()
                .resideInAnyPackage("hr.ericsson.etkc.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..hr.ericsson.etkc.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
