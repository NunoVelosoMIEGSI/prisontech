package com.dai.eventos.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dai.eventos.web.rest.TestUtil;

public class LogseventosTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Logseventos.class);
        Logseventos logseventos1 = new Logseventos();
        logseventos1.setId(1L);
        Logseventos logseventos2 = new Logseventos();
        logseventos2.setId(logseventos1.getId());
        assertThat(logseventos1).isEqualTo(logseventos2);
        logseventos2.setId(2L);
        assertThat(logseventos1).isNotEqualTo(logseventos2);
        logseventos1.setId(null);
        assertThat(logseventos1).isNotEqualTo(logseventos2);
    }
}
