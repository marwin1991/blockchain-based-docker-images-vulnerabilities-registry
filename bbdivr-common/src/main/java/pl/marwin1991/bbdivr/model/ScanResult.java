package pl.marwin1991.bbdivr.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ScanResult {

    private String scanToolName;

    private List<Layer> layers;

}
