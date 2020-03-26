package com.dai.eventos.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.dai.eventos.web.rest.TestUtil;

public class TipoeventoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tipoevento.class);
        Tipoevento tipoevento1 = new Tipoevento();
        tipoevento1.setId(1L);
        Tipoevento tipoevento2 = new Tipoevento();
        tipoevento2.setId(tipoevento1.getId());
        assertThat(tipoevento1).isEqualTo(tipoevento2);
        tipoevento2.setId(2L);
        assertThat(tipoevento1).isNotEqualTo(tipoevento2);
        tipoevento1.setId(null);
        assertThat(tipoevento1).isNotEqualTo(tipoevento2);
    }
}
