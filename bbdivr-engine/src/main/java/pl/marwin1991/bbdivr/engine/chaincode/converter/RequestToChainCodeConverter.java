package pl.marwin1991.bbdivr.engine.chaincode.converter;

import org.springframework.stereotype.Component;
import pl.marwin1991.bbdivr.chaincode.ChainCodeLayer;
import pl.marwin1991.bbdivr.chaincode.ChainCodeVulnerability;
import pl.marwin1991.bbdivr.engine.chaincode.model.AddLayerRequest;
import pl.marwin1991.bbdivr.model.Layer;
import pl.marwin1991.bbdivr.model.Vulnerability;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequestToChainCodeConverter {

    public ChainCodeLayer convert(Layer layer) {
        return ChainCodeLayer.builder()
                .id(layer.getId())
                .parentId(layer.getParentId() == null ? "" : layer.getParentId())
                .vulnerabilities(layer.getVulnerabilities())
                .vulnerabilitiesNamesFromParents(layer.getVulnerabilitiesNamesFromParents())
                .build();
    }

    public ChainCodeLayer convert(AddLayerRequest request) {
        return ChainCodeLayer.builder()
                .id(request.getId())
                .parentId(request.getParentId() == null ? "" : request.getParentId())
                .vulnerabilities(request.getVulnerabilities())
                .vulnerabilitiesNamesFromParents(request.getVulnerabilitiesNamesFromParents())
                .build();
    }

    private List<String> convert(List<Vulnerability> vulnerabilities) {
//        if(vulnerabilities.size() > 0)
//            return Collections.singletonList(convert(vulnerabilities.get(0)));
//
//        return Collections.emptyList();

//        return vulnerabilities.stream().map(this::convert).collect(Collectors.toList());
        return vulnerabilities.stream().map(Vulnerability::getVulnerabilityName).collect(Collectors.toList());
    }

    private ChainCodeVulnerability convert(Vulnerability v) {
        return ChainCodeVulnerability.builder()
                .vulnerabilityName(v.getVulnerabilityName())
                .featureName(v.getFeatureName())
                .featureVersion(v.getFeatureVersion())
                .featureVersionFormat(v.getFeatureVersionFormat())
                .namespaceName(v.getNamespaceName())
                //.description(v.getDescription())
                .link(v.getLink())
                .severity(v.getSeverity().getLevel())
                .fixedBy(v.getFixedBy())
                .build();
    }
}