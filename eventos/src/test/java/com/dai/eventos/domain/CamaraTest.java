package com.dai.eventos.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dai.eventos.web.rest.TestUtil;

public class CamaraTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Camara.class);
        Camara camara1 = new Camara();
        camara1.setId(1L);
        Camara camara2 = new Camara();
        camara2.setId(camara1.getId());
        assertThat(camara1).isEqualTo(camara2);
        camara2.setId(2L);
        assertThat(camara1).isNotEqualTo(camara2);
        camara1.setId(null);
        assertThat(camara1).isNotEqualTo(camara2);
    }
}
