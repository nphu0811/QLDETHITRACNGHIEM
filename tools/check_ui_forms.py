"""Verify form/source consistency and the UI-only scope; no business tests."""
from pathlib import Path
import re
import xml.etree.ElementTree as ET

BASE = Path(__file__).resolve().parents[1] / 'src/main/java'
new_packages = {'trangchu', 'quanly', 'thi', 'baocao', 'taikhoan', 'hethong'}
count = 0
for f in BASE.rglob('*.form'):
    root = ET.parse(f).getroot()
    source = f.with_suffix('.java').read_text(encoding='utf-8')
    components = root.findall('.//Component') + root.findall('.//Container')
    # Layout references are Component nodes too, but have id instead of name.
    components = [x for x in components if x.get('name')]
    names = [x.get('name') for x in components]
    assert len(names) == len(set(names)), (f, 'duplicate component names')
    for node in components:
        name = node.get('name')
        assert re.search(r'private\s+' + re.escape(node.get('class')) + r'\s+' + re.escape(name) + r'\s*;', source), (f, name, 'declaration mismatch')
        for prop in node.findall('./Properties/Property[@name="text"]'):
            assert prop.get('value') in source, (f, name, 'text mismatch')
    for layout in root.findall('.//Layout'):
        if layout.get('class'):
            continue
        actual = []
        for dim in ('0', '1'):
            group = layout.find(f'DimensionLayout[@dim="{dim}"]/Group')
            assert group is not None and group.get('type') == '103', (f, 'root must be parallel')
            ids = [x.get('id') for x in group.iter('Component')]
            assert len(ids) == len(set(ids)), (f, 'duplicate layout references')
            actual.append(set(ids))
        assert actual[0] == actual[1], (f, 'missing dimension')
    for event in root.findall('.//EventHandler'):
        handler = event.get('handler')
        assert f'void {handler}(java.awt.event.ActionEvent evt)' in source, (f, handler)
        if f.parent.name in new_packages:
            match = re.search(r'void ' + re.escape(handler) + r'\([^)]*\)\s*\{(.*?)\}', source, re.S)
            body = re.sub(r'//[^\n]*', '', match.group(1)).strip()
            assert not body, (f, handler, 'business handler must be empty')
    if f.parent.name in new_packages:
        assert not re.search(r'java\.sql|connectionSQLServer|executeUpdate|executeQuery|JOptionPane|javax\.swing\.Timer|PrinterJob|setVisible\(false\)', source), (f, 'business implementation')
        for table in root.findall('.//Table'):
            assert table.get('rowCount') == '0', (f, 'fake records')
    count += 1
assert count == 18, count
print(f'PASS: {count} form/source pairs; matching layout dimensions; empty new business handlers; no fake records.')
