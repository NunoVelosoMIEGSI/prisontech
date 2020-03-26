package com.dai.eventos;

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
            .importPackages("com.dai.eventos");

        noClasses()
            .that()
                .resideInAnyPackage("com.dai.eventos.service..")
            .or()
                .resideInAnyPackage("com.dai.eventos.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.dai.eventos.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
